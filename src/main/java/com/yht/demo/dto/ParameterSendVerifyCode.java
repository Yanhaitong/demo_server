package com.yht.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "发送验证码参数")
@Data
public class ParameterSendVerifyCode extends ParameterBase{

    @ApiModelProperty(name = "mobileNo", value = "手机号", required = true)
    private String mobileNo;

}
