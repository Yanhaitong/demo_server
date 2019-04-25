package com.yht.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "发送验证码参数")
@Data
public class ParameterSendVerifyCode {

    @ApiModelProperty(name = "clientType", value = "客户端类型（0:ios，1:android）", required = true)
    private String clientType;

    @ApiModelProperty(name = "clientName", value = "客户端名称（app名称）", required = true)
    private String clientName;

    @ApiModelProperty(name = "phoneIdentification", value = "手机唯一标示", required = false)
    private String phoneIdentification;

    @ApiModelProperty(name = "phoneModel", value = "手机型号", required = false)
    private String phoneModel;

    @ApiModelProperty(name = "phoneVersion", value = "手机系统版本", required = false)
    private String phoneVersion;

    @ApiModelProperty(name = "appVersion", value = "APP版本号", required = false)
    private String appVersion;

    @ApiModelProperty(name = "ip", value = "IP地址", required = false)
    private String ip;

    @ApiModelProperty(name = "mobileNo", value = "手机号", required = true)
    private String mobileNo;

}
