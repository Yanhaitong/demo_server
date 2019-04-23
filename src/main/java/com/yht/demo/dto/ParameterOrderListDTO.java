package com.yht.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yanht
 * @since 2019-04-19
 */
@ApiModel(value = "查询首页订单列表参数")
@Data
public class ParameterOrderListDTO extends ParameterBaseDTO {

    @ApiModelProperty(name = "citys", value = "城市（多个选项英文逗号隔开）", required = true)
    private String citys;

    @ApiModelProperty(name = "canRobOrder", value = "可抢订单（0:否，1:是）", required = true)
    private String canRobOrder;

    @ApiModelProperty(name = "navigationId", value = "导航id", required = true)
    private String navigationId;

    @ApiModelProperty(name = "loanAmountStart", value = "贷款额度开始", required = false)
    private String loanAmountStart;
    @ApiModelProperty(name = "loanAmountEnd", value = "贷款额度结束", required = false)
    private String loanAmountEnd;

    @ApiModelProperty(name = "incomeAmountStart", value = "月收入开始", required = false)
    private String incomeAmountStart;
    @ApiModelProperty(name = "incomeAmountEnd", value = "月收入结束", required = false)
    private String incomeAmountEnd;

    @ApiModelProperty(name = "incomeTypes", value = "收入形式（多选英文逗号隔开）", required = false)
    private String incomeTypes;

    @ApiModelProperty(name = "qualificationInfos", value = "资质信息（多选英文逗号隔开）", required = false)
    private String qualificationInfos;

    @ApiModelProperty(name = "pageSize", value = "每页个数", required = true)
    private Integer pageSize;

    @ApiModelProperty(name = "pageNum", value = "页数", required = true)
    private Integer pageNum;

}