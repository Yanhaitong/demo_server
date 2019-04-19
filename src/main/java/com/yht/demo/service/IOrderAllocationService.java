package com.yht.demo.service;

import com.yht.demo.common.Result;
import com.yht.demo.entity.OrderAllocation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yht.demo.entity.dto.OrderListReceiveDTO;

/**
 * <p>
 * 抢单记录表 服务类
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
public interface IOrderAllocationService extends IService<OrderAllocation> {

    Result vieForOrder(OrderListReceiveDTO orderListReceiveDTO);
}
