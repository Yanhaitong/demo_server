package com.yht.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 抢单记录表
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_order_allocation")
public class OrderAllocation extends Model<OrderAllocation> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 客户端
     */
    private String clientId;

    /**
     * 原始价格
     */
    private Integer originalPrice;

    /**
     * 实际成交价格
     */
    private Integer practicalPrice;

    /**
     * 订单类型（0:普通订单，1:折扣单）
     */
    private Integer orderType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除（0:未删除  1:已删除）
     */
    private Integer delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
