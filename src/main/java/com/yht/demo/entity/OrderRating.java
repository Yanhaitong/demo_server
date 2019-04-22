package com.yht.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单评级价格表
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_order_rating")
public class OrderRating extends Model<OrderRating> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 订单评级
     */
    private Integer rating;

    /**
     * 价格
     */
    private Integer price;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
