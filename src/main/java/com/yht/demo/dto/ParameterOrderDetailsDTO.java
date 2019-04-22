package com.yht.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
@ApiModel(value = "查询订单详细信息的参数")
@Data
public class ParameterOrderDetailsDTO {

    @ApiModelProperty(name = "token", value = "用户登录token", required = true)
    private String token;

    @ApiModelProperty(name = "orderId", value = "订单Id", required = true)
    private String orderId;

}