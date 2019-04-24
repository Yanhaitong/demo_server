package com.yht.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yanht
 * @since 2019-04-19
 */
@ApiModel(value = "查询订单详细信息的参数")
@Data
public class ParameterOrderDetailsDTO extends ParameterBaseDTO {

    @ApiModelProperty(name = "orderId", value = "订单Id", required = true)
    private String orderId;

}