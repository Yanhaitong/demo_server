package com.yht.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yht.demo.common.BaseServiceImpl;
import com.yht.demo.common.MsgConstant;
import com.yht.demo.common.RedisUtils;
import com.yht.demo.common.Result;
import com.yht.demo.dto.ParameterAmaldarOrderListDTO;
import com.yht.demo.dto.ParameterVieForOrderDTO;
import com.yht.demo.dto.ResultOrderDetailsDTO;
import com.yht.demo.entity.*;
import com.yht.demo.mapper.*;
import com.yht.demo.service.IOrderAllocationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 抢单记录表 服务实现类
 * </p>
 *
 * @author generator
 * @since 2019-04-22
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
    private SmsConfigMapper smsConfigMapper;
    @Autowired
    private ClientMapper clientMapper;

    @Override
    public Result vieForOrder(ParameterVieForOrderDTO parameterVieForOrderDTO) {
        try {
            //查询用户信息
            String userId = RedisUtils.getUserIdByToken(parameterVieForOrderDTO.getToken());
            if (userId == null) {
                return Result.error(500, MsgConstant.USER_ID_IS_NULL);
            }
            User userInfo = userMapper.selectById(userId);
            if (userInfo == null) {
                return Result.error(500, "用户信息错误！");
            }else {
                if (userInfo.getStatus() == 0){
                    return Result.error(500, "请先认证信贷经理！");
                }else if (userInfo.getStatus() == 1){
                    return Result.error(500, "经理认证审核中，请耐心等待！");
                }else if (userInfo.getStatus() == 3){
                    return Result.error(500, "经理认证未通过，请联系客服！");
                }else if (userInfo.getStatus() == 4){
                    return Result.error(500, "账户冻结，请联系客服！");
                }
            }
            Order order = orderMapper.selectById(parameterVieForOrderDTO.getOrderId());
            if (order == null) {
                return Result.error(500, "订单信息异常！");
            }
            MemberLevel memberLevel = memberLevelMapperl.selectById(userInfo.getMemberLevelId());
            if (memberLevel == null) {
                return Result.error(500, "会员等级异常！");
            } else {
                long vieForOrderCount = RedisUtils.getVieForOrderCount(userInfo.getId());
                if (vieForOrderCount > memberLevel.getVieForCount()) {
                    return Result.error(500, "\"您今日已抢了" + memberLevel.getVieForCount() + "单，明天再来吧，劳逸结合效益高哦\"");
                }
            }
            OrderRating orderRating = orderRatingMapper.selectByRating(order.getOrderRating());
            if (orderRating == null) {
                return Result.error(500, "订单等级异常！");
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
                //String smsContent = smsConfigMapper.getValueByKey("QIANGDANSMS" + parameterVieForOrderDTO.getClientName());
                /*if (StringUtils.isNotBlank(smsContent)) {
                    smsContent = smsContent.replace("{a}", order.getName());
                    smsContent = smsContent.replace("{b}", userInfo.getCompany());
                    smsContent = smsContent.replace("{c}", userInfo.getName());
                    SMSUtils.sendVerifyLoginSMS(mobileNo, smsContent);
                }*/
                //log.info("抢单后发短信内容" + smsContent);
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
    public Result amaldarOrderList(ParameterAmaldarOrderListDTO parameterAmaldarOrderListDTO) {
        String userId = RedisUtils.getUserIdByToken(parameterAmaldarOrderListDTO.getToken());
        if (StringUtils.isEmpty(userId)) {
            return Result.error(500, MsgConstant.USER_ID_IS_NULL);
        }
        User userInfo = userMapper.selectById(userId);
        Page page = new Page();
        page.setSize(parameterAmaldarOrderListDTO.getPageSize());
        page.setCurrent(parameterAmaldarOrderListDTO.getPageNum());
        IPage<ResultOrderDetailsDTO> resultOrderDetailsDTOIPage = orderAllocationMapper.amaldarOrderList(page, userInfo.getId());
        return Result.success(resultOrderDetailsDTOIPage);
    }
}
