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
public class ResultUserInfoDTO {

    @ApiModelProperty(name = "mobileNo", value = "手机号", required = true)
    private String mobileNo;

    @ApiModelProperty(name = "status", value = "经理状态（0:未认证，1:审核中，2:认证通过，3:认证未通过，4:账户冻结）", required = true)
    private String status;

    @ApiModelProperty(name = "company", value = "公司名称", required = true)
    private String company;

    @ApiModelProperty(name = "iconUrl", value = "头像", required = true)
    private String iconUrl;

    @ApiModelProperty(name = "balance", value = "余额", required = true)
    private String balance;

    @ApiModelProperty(name = "clientId", value = "客户端Id", required = true)
    private String clientId;

    @ApiModelProperty(name = "memberName", value = "会员等级名称", required = true)
    private String memberName;

}