package com.yht.demo.dto;

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
@ApiModel(value = "返回订单列表数据")
@Data
public class ResultOrderListDTO {

    @ApiModelProperty(name = "status", value = "是否已抢订单（0:未抢单，1:已抢单）", required = true)
    private String status;

    @ApiModelProperty(name = "name", value = "姓名", required = true)
    private String name;

    @ApiModelProperty(name = "incomeType", value = "收入类型", required = true)
    private String incomeType;

    @ApiModelProperty(name = "creditCardLimit", value = "信用额度", required = true)
    private String creditCardLimit;

    @ApiModelProperty(name = "loanPurpose", value = "消费类型", required = true)
    private String loanPurpose;

    @ApiModelProperty(name = "socialSecurity", value = "社保信息", required = true)
    private String socialSecurity;

    @ApiModelProperty(name = "estateInfo", value = "房产信息", required = true)
    private String estateInfo;

    @ApiModelProperty(name = "carInfo", value = "车产信息", required = true)
    private String carInfo;

    @ApiModelProperty(name = "insuranceInfo", value = "保险信息", required = true)
    private String insuranceInfo;

    @ApiModelProperty(name = "smallLabel", value = "小标签（姓名后边的文字标记）", required = true)
    private String smallLabel;

    @ApiModelProperty(name = "loanAmount", value = "贷款金额", required = true)
    private String loanAmount;

    @ApiModelProperty(name = "loanPeriod", value = "还款期限", required = true)
    private String loanPeriod;

    @ApiModelProperty(name = "currentCity", value = "当前城市", required = true)
    private String currentCity;

    @ApiModelProperty(name = "incomeAmount", value = "月收入", required = true)
    private String incomeAmount;

    @ApiModelProperty(name = "familyCity", value = "户口信息", required = true)
    private String familyCity;

    @ApiModelProperty(name = "createOrderTime", value = "创建订单时间", required = true)
    private String createOrderTime;



}