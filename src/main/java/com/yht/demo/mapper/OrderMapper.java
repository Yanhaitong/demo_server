package com.yht.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yht.demo.entity.dto.OrderDetailsReturnDTO;
import com.yht.demo.entity.dto.OrderListReceiveDTO;
import com.yht.demo.entity.model.Order;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
public interface OrderMapper extends BaseMapper<Order> {

    IPage<OrderDetailsReturnDTO> selectOrderListByMap(Page page, OrderListReceiveDTO orderListReceiveDTO);

    OrderDetailsReturnDTO getOrderDetailsById(String orderId);

    OrderDetailsReturnDTO amaldarOrderList(Integer userId);
}
