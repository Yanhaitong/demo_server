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
public class OrderDetailsReturnDTO {

    @ApiModelProperty(name = "name", value = "姓名", required = true)
    private String name;

    @ApiModelProperty(name = "age", value = "年龄", required = true)
    private String age;

    @ApiModelProperty(name = "loanAmount", value = "贷款金额（元）", required = true)
    private String loanAmount;

    @ApiModelProperty(name = "loanPurpose", value = "贷款目的", required = true)
    private String loanPurpose;

    @ApiModelProperty(name = "currentCity", value = "当前城市", required = true)
    private String currentCity;

    @ApiModelProperty(name = "professionalType", value = "职业类型", required = true)
    private String professionalType;

    @ApiModelProperty(name = "levelEducation", value = "文化程度", required = true)
    private String levelEducation;

    @ApiModelProperty(name = "familyCity", value = "户籍城市", required = true)
    private String familyCity;

    @ApiModelProperty(name = "incomeAmount", value = "收入情况（元）", required = true)
    private String incomeAmount;

    @ApiModelProperty(name = "incomeType", value = "收入形式", required = true)
    private String incomeType;

    @ApiModelProperty(name = "workingYears", value = "当前单位工龄", required = true)
    private String workingYears;

    @ApiModelProperty(name = "socialSecurity", value = "社保", required = true)
    private String socialSecurity;

    @ApiModelProperty(name = "accumulationFund", value = "公积金", required = true)
    private String accumulationFund;

    @ApiModelProperty(name = "estateInfo", value = "房产信息", required = true)
    private String estateInfo;

    @ApiModelProperty(name = "estateValues", value = "房产价值", required = true)
    private String estateValues;

    @ApiModelProperty(name = "carInfo", value = "车产信息", required = true)
    private String carInfo;

    @ApiModelProperty(name = "carValues", value = "车产估值", required = true)
    private String carValues;

}