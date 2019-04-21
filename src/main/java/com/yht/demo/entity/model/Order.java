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
 * 订单表
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order_")
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
     * 申请时间
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
     * 文化程度
     */
    private String levelEducation;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 职业类型（0:上班族，1:公务员/事业编制，2:自由职业，3:个体户，4:企业主）
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
     * 房产类型（0:无，1:商品住宅，2:商铺，3:办公楼，4:厂房，5:经济适用房，6:其他）
     */
    private Integer estateInfo;

    /**
     * 房产价值（0:无，1:50万以下，2:50万-100万，3:100万-200万，4:200万以上）
     */
    private Integer estateValues;

    /**
     * 车产类型（0:无，1:无车准备购买，2:有车不接受抵押，3:有车可接受抵押 ）
     */
    private Integer carInfo;

    /**
     * 车产估值（0:无，1:10万以下，2:10-20万，3:20-30万，4:30万以上）
     */
    private Integer carValues;

    /**
     * 保险信息（0:无，1:中国人寿，2:中国平安，3:太平洋保险，4:中国太平，5:阳光保险，6:安邦保险，7:前海人寿，8:泰康人寿，9:新华保险，10:其他）
     */
    private Integer insuranceInfo;

    /**
     * 保险价值（0:无，1:10万以下，2:10-30万，3:30-50万，4:50万以上）
     */
    private Integer insuranceValues;

    /**
     * 信用卡额度（0:无，1:1万以下，2:1-5万，3:5万以上）
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
    private String orderRating;
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
