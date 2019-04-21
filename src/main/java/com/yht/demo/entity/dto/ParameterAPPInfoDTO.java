package com.yht.demo.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "查询用户相关信息的参数")
@Data
public class ParameterAPPInfoDTO {

    @ApiModelProperty(name = "token", value = "用户登录token", required = true)
    private String token;

    @ApiModelProperty(name = "clientName", value = "客户端名称", required = true)
    private String clientName;

}
