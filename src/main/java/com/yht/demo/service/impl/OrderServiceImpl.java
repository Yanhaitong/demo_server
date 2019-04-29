package com.yht.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yht.demo.common.BaseServiceImpl;
import com.yht.demo.common.MsgConstant;
import com.yht.demo.common.RedisUtils;
import com.yht.demo.common.Result;
import com.yht.demo.dto.*;
import com.yht.demo.entity.Order;
import com.yht.demo.entity.OrderAllocation;
import com.yht.demo.entity.SearchConditions;
import com.yht.demo.mapper.BannerMapper;
import com.yht.demo.mapper.OrderAllocationMapper;
import com.yht.demo.mapper.OrderMapper;
import com.yht.demo.mapper.SearchConditionsMapper;
import com.yht.demo.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderAllocationMapper orderAllocationMapper;
    @Autowired
    private BannerMapper bannerMapper;
    @Autowired
    private SearchConditionsMapper searchConditionsMapper;

    @Override
    public Result getHomePageOrderList(ParameterOrderListDTO parameterOrderListDTO) {
        //bannber列表
        List<ResultBannerDTO> bannerList = bannerMapper.selectBannerListByMap(parameterOrderListDTO.getClientId());

        //首页列表
       /* if (!StringUtils.isEmpty(parameterOrderListDTO.getQualificationInfos())){
            String[] incomeTypes = parameterOrderListDTO.getIncomeTypes().split(",");
            List<String> incomeTypeList = Arrays.asList(incomeTypes);
            parameterOrderListDTO.setIncomeTypeList(incomeTypeList);
            String[] strings = parameterOrderListDTO.getQualificationInfos().split(",");
            for (String id : strings) {
                SearchConditions searchConditions = searchConditionsMapper.selectById(id);
                if ("有社保".equals(searchConditions.getSearchName())){
                    parameterOrderListDTO.setSocialSecurity(1);
                }else if ("有公积金".equals(searchConditions.getSearchName())){
                    parameterOrderListDTO.setSocialSecurity(1);
                }else if ("有微粒贷".equals(searchConditions.getSearchName())){
                    parameterOrderListDTO.setWeilidai(1);
                }
            }
        }*/

        Page page = new Page();
        page.setSize(parameterOrderListDTO.getPageSize());
        page.setCurrent(parameterOrderListDTO.getPageNum());
        IPage<ResultOrderListDTO> resultOrderDetailsDTOIPage = orderMapper.selectOrderListByMap(page, parameterOrderListDTO);

        ResultHomeOrderListDTO resultHomeOrderListDTO = new ResultHomeOrderListDTO();
        resultHomeOrderListDTO.setBannerList(bannerList);
        resultHomeOrderListDTO.setOrderList(resultOrderDetailsDTOIPage);

        return Result.success(resultHomeOrderListDTO);
    }

    @Override
    public Result getOrderDetailsById(ParameterOrderDetailsDTO parameterOrderDetailsDTO) {

        Integer myOrderInt = 0;
        //查询此订单是否是当前用户已抢订单
        Order order = orderMapper.selectById(parameterOrderDetailsDTO.getOrderId());
        if (!StringUtils.isEmpty(order.getStatus()) && order.getStatus() != 0){
            String userId = RedisUtils.getUserIdByToken(parameterOrderDetailsDTO.getToken());
            if (StringUtils.isEmpty(userId)){
                return Result.error(500, MsgConstant.USER_ID_IS_NULL);
            }
            Map<String, Object> parameterMap = new HashMap<>();
            parameterMap.put("order_id", order.getId());
            parameterMap.put("user_id", userId);
            List<OrderAllocation> orderAllocationList = orderAllocationMapper.selectByMap(parameterMap);
            if (orderAllocationList.size() >= 1){//此订单是当前用户已抢订单
                myOrderInt = 1;
            }
        }

        ResultOrderDetailsDTO resultOrderDetailsDTO = orderMapper.getOrderDetailsById(parameterOrderDetailsDTO.getOrderId(), myOrderInt);
        return Result.success(resultOrderDetailsDTO);
    }

    @Override
    public Result<ResultSearchConditionsDTO> getSearchConditionsList(ParameterBaseDTO parameterBaseDTO) {
        List<ResultSearchConditionsDTO> searchConditionsList = searchConditionsMapper.getSearchConditionsList(parameterBaseDTO.getClientId());
        return Result.success(searchConditionsList);
    }
}
