package com.yht.demo.mapper;

import com.yht.demo.entity.dto.OrderListReceiveDTO;
import com.yht.demo.entity.dto.OrderListReturnDTO;
import com.yht.demo.entity.model.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
public interface OrderMapper extends BaseMapper<Order> {

    List<OrderListReturnDTO> selectOrderListByMap(OrderListReceiveDTO orderListReceiveDTO);
}
