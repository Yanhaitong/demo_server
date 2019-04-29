package com.yht.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author generator
 * @since 2019-04-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_order")
public class Order extends Model<Order> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单状态（0:未抢单，1:已抢单，2:已退单）
     */
    private Integer status;

    /**
     * 手机号
     */
    private String mobileNo;

    /**
     * 申请时间
     */
    private Date applyTime;

    /**
     * 抢单时间
     */
    private Date vieForTime;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄（周岁）
     */
    private Integer age;

    /**
     * 性别（0:女，1:男）
     */
    private Integer sex;

    /**
     * 贷款金额（元）
     */
    private String loanAmount;

    /**
     * 贷款期限（月）
     */
    private String loanPeriod;

    /**
     * 贷款目的（0:消费贷款，1:结婚贷款，2:购房贷款，3:购车贷款，4:装修贷款，5:旅游贷款，6:教育培训贷款，7:其他贷款）
     */
    private Integer loanPurpose;

    /**
     * 身份证号码
     */
    private String idNumber;

    /**
     * 当前所在城市
     */
    private String currentCity;

    /**
     * 手机号码归属地
     */
    private String phoneAttribution;

    /**
     * 户籍城市
     */
    private String familyCity;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 文化程度（0.高中，1:大专，2:本科，3:研究生）
     */
    private Integer levelEducation;

    /**
     * 职业类型（0:上班族，1:公务员/事业编制，2:个体户，3:企业主）
     */
    private Integer professionalType;

    /**
     * 收入情况(月收入)
     */
    private String incomeAmount;

    /**
     * 收入形式（0:银行代发，1:转账工资，2:现金发放）
     */
    private Integer incomeType;

    /**
     * 当前单位工龄（0:6个月以下，1:6-12个月，2:12个月以上）
     */
    private Integer workingYears;

    /**
     * 社保（0:无，1:6个月以下，2:6-12个月，3:12个月以上）
     */
    private Integer socialSecurity;

    /**
     * 公积金（0:无，1:6个月以下，2:6-12个月，3:12个月以上）
     */
    private Integer accumulationFund;

    /**
     * 房产信息（0:无，1:有）
     */
    private Integer estateInfo;

    /**
     * 车产信息（0:无，1:有 ）
     */
    private Integer carInfo;

    /**
     * 保险信息（0:无，1:有）
     */
    private Integer insuranceInfo;

    /**
     * 信用卡（0:无，1:有）
     */
    private Integer creditCardLimit;

    /**
     * 芝麻分数
     */
    private String sesameScores;

    /**
     * 微粒贷额度
     */
    private String weilidaiLimit;

    /**
     * 主渠道号
     */
    private Integer shareId;

    /**
     * 子渠道号
     */
    private Integer partnerId;

    /**
     * 数据来源（0:api导单，1:H5采量）
     */
    private Integer orderSource;

    /**
     * 订单评级
     */
    private Integer orderRating;

    /**
     * 申请ip
     */
    private String applyIp;

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
