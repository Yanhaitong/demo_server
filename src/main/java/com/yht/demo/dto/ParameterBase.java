package com.yht.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 终端公共参数
 */
@Data
public class ParameterBase {

    @ApiModelProperty(name = "clientType", value = "客户端类型（0:ios，1:android）", required = true)
    private String clientType;

    @ApiModelProperty(name = "clientId", value = "客户端Id", required = true)
    private String clientId;

    @ApiModelProperty(name = "deviceIdentification", value = "设备唯一标示", required = false)
    private String deviceIdentification;

    @ApiModelProperty(name = "deviceModel", value = "设备型号", required = false)
    private String deviceModel;

    @ApiModelProperty(name = "version", value = "APP版本号", required = false)
    private String version;

    @ApiModelProperty(name = "ip", value = "IP地址", required = false)
    private String ip;

}
