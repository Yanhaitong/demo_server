package com.yht.demo.mapper;

import com.yht.demo.entity.OrderRating;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 订单评级价格表 Mapper 接口
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
public interface OrderRatingMapper extends BaseMapper<OrderRating> {

    OrderRating selectByRating(Integer orderRating);
}
