package com.yht.demo.service;

import com.yht.demo.common.Result;
import com.yht.demo.entity.dto.ParameterOrderListDTO;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
public interface IOrderService {

    Result getHomePageOrderList(ParameterOrderListDTO parameterOrderListDTO);

    Result getOrderDetailsById(String orderId);
}
