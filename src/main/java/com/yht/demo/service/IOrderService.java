package com.yht.demo.service;

import com.yht.demo.common.Result;
import com.yht.demo.dto.ParameterOrderDetailsDTO;
import com.yht.demo.dto.ParameterOrderListDTO;
import com.yht.demo.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
public interface IOrderService  {

    Result getHomePageOrderList(ParameterOrderListDTO parameterOrderListDTO);

    Result getOrderDetailsById(ParameterOrderDetailsDTO parameterOrderDetailsDTO);
}
