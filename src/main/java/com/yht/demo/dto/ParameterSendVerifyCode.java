package com.yht.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "发送验证码参数")
@Data
public class ParameterSendVerifyCode {

    @ApiModelProperty(name = "mobileNo", value = "手机号", required = true)
    private String mobileNo;

    @ApiModelProperty(name = "clientName", value = "客户端名称", required = true)
    private String clientName;
}
