package com.yht.demo.service.impl;

import com.yht.demo.common.BaseServiceImpl;
import com.yht.demo.common.Result;
import com.yht.demo.entity.dto.OrderListReceiveDTO;
import com.yht.demo.entity.dto.OrderListReturnDTO;
import com.yht.demo.entity.model.Order;
import com.yht.demo.mapper.OrderMapper;
import com.yht.demo.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Result getHomePageOrderList(OrderListReceiveDTO orderListReceiveDTO) {
        List<OrderListReturnDTO> orderListReturnDTOList = orderMapper.selectOrderListByMap(orderListReceiveDTO);
        return Result.success(orderListReturnDTOList);
    }

    @Override
    public Result getHomePageCityList(OrderListReceiveDTO orderListReceiveDTO) {
        return null;
    }

    @Override
    public Result getHomePageSearchConditions(OrderListReceiveDTO orderListReceiveDTO) {
        return null;
    }

    @Override
    public Result getAllOrderInfo(OrderListReceiveDTO orderListReceiveDTO) {
        return null;
    }

    @Override
    public Result myOrderList(OrderListReceiveDTO orderListReceiveDTO) {
        return null;
    }
}
