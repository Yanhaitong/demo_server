package com.yht.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "接收用户登录参数")
@Data
public class ParameterUserDTO {

    @ApiModelProperty(name = "mobileNo", value = "手机号", required = true)
    private String mobileNo;

    @ApiModelProperty(name = "clientName", value = "客户端姓名", required = true)
    private String clientName;

    @ApiModelProperty(name = "version", value = "系统版本号", required = true)
    private String version;

    @ApiModelProperty(name = "clientType", value = "客户端类型（0:ios 1:android）", required = true)
    private String clientType;

    @ApiModelProperty(name = "code", value = "验证码", required = true)
    private String code;

}
