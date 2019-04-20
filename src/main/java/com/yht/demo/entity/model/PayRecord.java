package com.yht.demo.entity.model;

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
 * 充值记录表
 * </p>
 *
 * @author yanht
 * @since 2019-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pay_record")
public class PayRecord extends Model<PayRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id_", type = IdType.AUTO)
    private Integer id;

    /**
     * 经理流水（0:充值，1:退单，2:抢单消费，3:手动修改）
     */
    private Integer type;

    /**
     * 支付订单id
     */
    private String orderNo;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 手机号
     */
    private String mobileNo;

    /**
     * 充值金额
     */
    private Integer money;

    /**
     * 支付状态（0:失败，1:成功）
     */
    private Integer status;

    /**
     * 客户端名称
     */
    private String clientName;

    /**
     * 客户端类型（0:ios，1:android）
     */
    private Integer clientType;

    /**
     * 充值完成后余额
     */
    private Integer totalMoney;

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
