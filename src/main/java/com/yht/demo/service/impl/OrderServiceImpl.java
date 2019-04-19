package com.yht.demo.service.impl;

import com.yht.demo.common.Result;
import com.yht.demo.entity.dto.OrderListReceiveDTO;
import com.yht.demo.entity.model.Order;
import com.yht.demo.mapper.OrderMapper;
import com.yht.demo.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Override
    public Result getHomePageOrderList(OrderListReceiveDTO orderListReceiveDTO) {
        return null;
    }

    @Override
    public Result getHomePageNavigationList(OrderListReceiveDTO orderListReceiveDTO) {
        return null;
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
