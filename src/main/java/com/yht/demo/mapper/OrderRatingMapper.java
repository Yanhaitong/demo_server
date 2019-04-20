package com.yht.demo.mapper;

import com.yht.demo.entity.model.OrderRating;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 订单评级价格表 Mapper 接口
 * </p>
 *
 * @author yanht
 * @since 2019-04-20
 */
public interface OrderRatingMapper extends BaseMapper<OrderRating> {

    OrderRating selectByRating(String orderRating);
}
