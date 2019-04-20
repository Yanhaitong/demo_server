package com.yht.demo.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
@ApiModel(value = "返回首页列表参数")
@Data
public class OrderListReturnDTO {

    @ApiModelProperty(name = "status", value = "可抢订单（0:不可抢订单，1:可抢订单）", required = true)
    private String status;

    @ApiModelProperty(name = "loanAmountStart", value = "贷款额度开始", required = true)
    private String loanAmountStart;
    @ApiModelProperty(name = "loanAmountEnd", value = "贷款额度结束", required = true)
    private String loanAmountEnd;

    @ApiModelProperty(name = "loanPeriodStart", value = "贷款期限开始", required = true)
    private String loanPeriodStart;
    @ApiModelProperty(name = "loanPeriodEnd", value = "贷款期限结束", required = true)
    private String loanPeriodEnd;

    @ApiModelProperty(name = "ageStart", value = "年龄区间开始", required = true)
    private String ageStart;
    @ApiModelProperty(name = "ageEnd", value = "年龄区间结束", required = true)
    private String ageEnd;

    @ApiModelProperty(name = "incomeAmountStart", value = "月收入开始", required = true)
    private String incomeAmountStart;
    @ApiModelProperty(name = "incomeAmountEnd", value = "月收入结束", required = true)
    private String incomeAmountEnd;

    @ApiModelProperty(name = "incomeType", value = "收入形式（0:银行代发，1:转账工资，2:现金发放）", required = true)
    private String incomeType;

    @ApiModelProperty(name = "qualificationInfo", value = "资质信息", required = true)
    private String qualificationInfo;

}