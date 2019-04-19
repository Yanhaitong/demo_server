package com.yht.demo.service;

import com.yht.demo.common.Result;
import com.yht.demo.entity.dto.OrderListReceiveDTO;
import com.yht.demo.entity.model.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
public interface IOrderService extends IService<Order> {

    Result getHomePageOrderList(OrderListReceiveDTO orderListReceiveDTO);

    Result getHomePageCityList(OrderListReceiveDTO orderListReceiveDTO);

    Result getHomePageSearchConditions(OrderListReceiveDTO orderListReceiveDTO);

    Result getAllOrderInfo(OrderListReceiveDTO orderListReceiveDTO);

    Result myOrderList(OrderListReceiveDTO orderListReceiveDTO);
}
