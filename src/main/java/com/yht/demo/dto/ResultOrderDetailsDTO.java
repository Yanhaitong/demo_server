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
@ApiModel(value = "返回订单详细参数")
@Data
public class ResultOrderDetailsDTO {

    @ApiModelProperty(name = "status", value = "是否已抢订单（0:未抢单，1:已抢单）", required = true)
    private String status;

    @ApiModelProperty(name = "name", value = "姓名", required = true)
    private String name;

    @ApiModelProperty(name = "mobileNo", value = "手机号", required = true)
    private String mobileNo;

    @ApiModelProperty(name = "age", value = "年龄", required = true)
    private String age;

    @ApiModelProperty(name = "levelEducation", value = "学历", required = true)
    private String levelEducation;

    @ApiModelProperty(name = "familyCity", value = "户籍城市", required = true)
    private String familyCity;

    @ApiModelProperty(name = "phoneBelonging", value = "手机归属地", required = true)
    private String phoneBelonging;

    @ApiModelProperty(name = "incomeAmount", value = "月收入", required = true)
    private String incomeAmount;

    @ApiModelProperty(name = "incomeType", value = "收入形式", required = true)
    private String incomeType;

    @ApiModelProperty(name = "companyName", value = "公司名称", required = true)
    private String companyName;

    @ApiModelProperty(name = "workingYears", value = "当前公司工龄", required = true)
    private String workingYears;

    @ApiModelProperty(name = "professionalType", value = "职业类型", required = true)
    private String professionalType;

    @ApiModelProperty(name = "socialSecurity", value = "本地社保", required = true)
    private String socialSecurity;

    @ApiModelProperty(name = "accumulationFund", value = "本地公积金", required = true)
    private String accumulationFund;

    @ApiModelProperty(name = "estateInfo", value = "房产信息", required = true)
    private String estateInfo;

    @ApiModelProperty(name = "carInfo", value = "车产信息", required = true)
    private String carInfo;

    @ApiModelProperty(name = "insuranceInfo", value = "保险信息", required = true)
    private String insuranceInfo;

    @ApiModelProperty(name = "creditCardLimit", value = "信用卡额度", required = true)
    private String creditCardLimit;

    @ApiModelProperty(name = "weilidaiLimit", value = "微粒贷额度", required = true)
    private String weilidaiLimit;

    @ApiModelProperty(name = "sesameScores", value = "芝麻分数", required = true)
    private String sesameScores;

    @ApiModelProperty(name = "vieForTime", value = "抢单时间", required = true)
    private String vieForTime;

    @ApiModelProperty(name = "loanPeriod", value = "贷款期限", required = true)
    private String loanPeriod;

    @ApiModelProperty(name = "loanAmount", value = "贷款金额（元）", required = true)
    private String loanAmount;

    @ApiModelProperty(name = "loanPurpose", value = "贷款目的", required = true)
    private String loanPurpose;

    @ApiModelProperty(name = "currentCity", value = "当前城市", required = true)
    private String currentCity;

    @ApiModelProperty(name = "browseNumber", value = "当前浏览人数", required = true)
    private String browseNumber;


}