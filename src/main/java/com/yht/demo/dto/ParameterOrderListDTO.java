package com.yht.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author yanht
 * @since 2019-04-19
 */
@ApiModel(value = "查询首页订单列表参数")
@Data
public class ParameterOrderListDTO extends ParameterBaseDTO {

    @ApiModelProperty(name = "canRobOrder", value = "可抢订单（0:全部，1:可抢订单）", required = true)
    private String canRobOrder;

    @ApiModelProperty(name = "createOrderTime", value = "创建订单时间", required = true)
    private String createOrderTime;

    @ApiModelProperty(name = "navigationId", value = "导航id", required = true)
    private String navigationId;

    @ApiModelProperty(name = "citys", value = "城市（多个选项英文逗号隔开）", required = false)
    private String citys;

    @ApiModelProperty(name = "loanAmountStart", value = "贷款额度开始", required = false)
    private String loanAmountStart;
    @ApiModelProperty(name = "loanAmountEnd", value = "贷款额度结束", required = false)
    private String loanAmountEnd;

    @ApiModelProperty(name = "incomeAmountStart", value = "月收入开始", required = false)
    private String incomeAmountStart;
    @ApiModelProperty(name = "incomeAmountEnd", value = "月收入结束", required = false)
    private String incomeAmountEnd;

    @ApiModelProperty(name = "searchId", value = "动态选项的id（多个用英文逗号隔开）", required = false)
    private String searchId;

    @ApiModelProperty(name = "pageSize", value = "每页个数", required = true)
    private Integer pageSize;

    @ApiModelProperty(name = "pageNum", value = "页数", required = true)
    private Integer pageNum;

    //**************************隐藏字段（搜索项使用）****************************
    @ApiModelProperty(name = "socialSecurity", value = "有社保", hidden = true)
    private Integer socialSecurity;

    @ApiModelProperty(name = "accumulationFund", value = "有公积金", hidden = true)
    private Integer accumulationFund;

    @ApiModelProperty(name = "weilidai", value = "有微粒贷", hidden = true)
    private Integer weilidai;

    @ApiModelProperty(name = "incomeTypeList", value = "收入形式", hidden = true)
    private List<String> incomeTypeList;

}