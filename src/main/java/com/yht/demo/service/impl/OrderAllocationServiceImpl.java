package com.yht.demo.service.impl;

import com.yht.demo.common.Result;
import com.yht.demo.entity.OrderAllocation;
import com.yht.demo.entity.dto.OrderListReceiveDTO;
import com.yht.demo.mapper.OrderAllocationMapper;
import com.yht.demo.service.IOrderAllocationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 抢单记录表 服务实现类
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
@Service
public class OrderAllocationServiceImpl extends ServiceImpl<OrderAllocationMapper, OrderAllocation> implements IOrderAllocationService {

    @Override
    public Result vieForOrder(OrderListReceiveDTO orderListReceiveDTO) {
        return null;
    }
}
