package com.yht.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yht.demo.common.BaseServiceImpl;
import com.yht.demo.common.RedisUtils;
import com.yht.demo.common.Result;
import com.yht.demo.common.sender.SMSUtils;
import com.yht.demo.entity.dto.AmaldarOrderListReceiveDTO;
import com.yht.demo.entity.dto.OrderDetailsReturnDTO;
import com.yht.demo.entity.model.MemberLevel;
import com.yht.demo.entity.model.OrderRating;
import com.yht.demo.entity.model.Order;
import com.yht.demo.entity.model.OrderAllocation;
import com.yht.demo.entity.model.User;
import com.yht.demo.mapper.*;
import com.yht.demo.service.IOrderAllocationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 抢单记录表 服务实现类
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
@Service
public class OrderAllocationServiceImpl extends BaseServiceImpl implements IOrderAllocationService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderAllocationMapper orderAllocationMapper;
    @Autowired
    private PayRecordMapper payRecordMapper;
    @Autowired
    private MemberLevelMapper memberLevelMapperl;
    @Autowired
    private OrderRatingMapper orderRatingMapper;
    @Autowired
    private SystemConfigMapper systemConfigMapper;

    @Override
    public Result vieForOrder(String token, String orderId, String clientName) {
        try {
            //查询用户信息
            String mobileNo = RedisUtils.getMobileByToken(token);
            if (mobileNo == null) {
                return Result.error(500, "登录信息已失效，请重新登录！");
            }
            User userInfo = userMapper.getUserInfo(mobileNo, clientName);
            if (userInfo == null) {
                return Result.error(500, "抢单失败,用户信息错误！");
            }
            Order order = orderMapper.selectById(orderId);
            if (order == null) {
                return Result.error(500, "抢单失败,订单信息异常！");
            }
            MemberLevel memberLevel = memberLevelMapperl.selectById(userInfo.getLevelId());
            if (memberLevel == null) {
                return Result.error(500, "抢单失败,会员等级异常！");
            } else {
                long vieForOrderCount = RedisUtils.getVieForOrderCount(userInfo.getId());
                if (vieForOrderCount > memberLevel.getVieForCount()) {
                    return Result.error(500, "\"您今日已抢了" + memberLevel.getVieForCount() + "单，明天再来吧，劳逸结合效益高哦\"");
                }
            }
            OrderRating orderRating = orderRatingMapper.selectByRating(order.getOrderRating());
            if (orderRating == null) {
                return Result.error(500, "抢单失败,订单等级异常！");
            }

            //开始抢单
            if (order.getStatus() != 0) {
                return Result.error(500, "抢单失败,慢了一步！");
            } else {
                //防止并发抢单，单个订单只允许一人成功，防止并发往抢单记录表插入数据造成大量数据库错误，减轻数据库压力
                String vieOrderKey = "VIEORDER_KEY&" + order.getId();
                byte[] redisKey = vieOrderKey.getBytes();
                String threadName = Thread.currentThread().getName();
                RedisConnection rc = null;
                try {
                    rc = stringRedisTemplate.getConnectionFactory().getConnection();
                    rc.watch(redisKey);//开启监控key实际，以便开启事务支持
                    boolean b = rc.exists(redisKey);
                    if (!b) {
                        rc.multi();//开启事务支持
                        rc.set(redisKey, threadName.getBytes());
                        rc.expire(redisKey, (long) 60 * 60 * 1);
                        rc.exec();
                        String v = new String(rc.get(redisKey));
                        if (!threadName.equals(v)) {
                            return Result.error(500, "抢单失败,慢了一步！");
                        }
                    } else {
                        rc.unwatch();
                        return Result.error(500, "抢单失败,慢了一步！");
                    }
                } catch (Exception e) {
                    log.error("竞争抢单操作异常。。。。。");
                    throw new RuntimeException();
                } finally {
                    rc.close();
                }
            }

            //抢单成功修改订单表
            order.setStatus(0);
            order.setUpdateTime(new Date());
            order.setVieForTime(new Date());
            orderMapper.updateById(order);

            //抢单成功插入抢单记录表
            OrderAllocation orderAllocation = new OrderAllocation();
            orderAllocation.setOrderId(order.getId());
            orderAllocation.setOriginalPrice(orderRating.getPrice());
            orderAllocation.setPracticalPrice(orderRating.getPrice());
            orderAllocation.setOrderType(1);
            orderAllocation.setUpdateTime(new Date());
            orderAllocation.setCreateTime(new Date());
            orderAllocationMapper.insert(orderAllocation);

            try {
                //发送短信
                String smsContent = systemConfigMapper.getValueByKey("QIANGDANSMS" + userInfo.getClientName());
                /*if (StringUtils.isNotBlank(smsContent)) {
                    smsContent = smsContent.replace("{a}", order.getName());
                    smsContent = smsContent.replace("{b}", userInfo.getCompany());
                    smsContent = smsContent.replace("{c}", userInfo.getName());
                    SMSUtils.sendVerifyLoginSMS(mobileNo, smsContent);
                }*/
                log.info("抢单后发短信内容" + smsContent);
            } catch (Exception e) {
                log.error("发送短信失败====================" + e.getMessage());
            }

            Map<String, Object> parameterMap = new HashMap<>();
            parameterMap.put("mobileNo", order.getMobileNo());
            parameterMap.put("balance", userInfo.getBalance());
            return Result.success(parameterMap);

        } catch (Exception e) {
            log.error("抢单失败====================" + e.getMessage());
            return Result.error(500, "抢单失败！");
        }
    }

    @Override
    public Result amaldarOrderList(AmaldarOrderListReceiveDTO amaldarOrderListReceiveDTO) {
        String mobileNo = "18611556532";//RedisUtils.getMobileByToken(amaldarOrderListReceiveDTO.getToken());
        if (mobileNo == null){
            return Result.error(500, "登录信息已失效，请重新登录！");
        }
        User userInfo = userMapper.getUserInfo(mobileNo, amaldarOrderListReceiveDTO.getClientName());
        if (userInfo == null){
            return Result.error(500, "抢单失败,用户信息错误！！");
        }
        Page page = new Page();
        page.setSize(amaldarOrderListReceiveDTO.getPageSize());
        page.setCurrent(amaldarOrderListReceiveDTO.getPageNum());
        IPage<OrderDetailsReturnDTO> orderDetailsReturnDTOIPage = orderAllocationMapper.amaldarOrderList(page, userInfo.getId());
        return Result.success(orderDetailsReturnDTOIPage);
    }
}
