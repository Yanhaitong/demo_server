package com.yht.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "抢单接口参数")
@Data
public class ParameterVieForOrderDTO {

    @ApiModelProperty(name = "token", value = "token", required = true)
    private String token;

    @ApiModelProperty(name = "clientName", value = "客户端姓名", required = true)
    private String clientName;

    @ApiModelProperty(name = "orderId", value = "订单id", required = true)
    private String orderId;

}
