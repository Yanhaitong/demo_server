package com.yht.demo.service.impl;

import com.yht.demo.entity.model.OrderRating;
import com.yht.demo.mapper.OrderRatingMapper;
import com.yht.demo.service.IOrderRatingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单评级价格表 服务实现类
 * </p>
 *
 * @author yanht
 * @since 2019-04-20
 */
@Service
public class OrderRatingServiceImpl extends ServiceImpl<OrderRatingMapper, OrderRating> implements IOrderRatingService {

}
