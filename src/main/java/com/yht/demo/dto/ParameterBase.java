package com.yht.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 终端公共参数
 */
@Data
public class ParameterBase {

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

}
