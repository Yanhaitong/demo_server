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
@ApiModel(value = "返回订单详细参数")
@Data
public class ResultUserInfoDTO {

    @ApiModelProperty(name = "mobileNo", value = "手机号", required = true)
    private String mobileNo;

    @ApiModelProperty(name = "icon", value = "头像", required = true)
    private String icon;

}