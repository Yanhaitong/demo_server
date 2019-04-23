package com.yht.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "查询用户相关信息的参数")
@Data
public class ParameterUserInfoDTO extends ParameterBaseDTO {

    @ApiModelProperty(name = "token", value = "用户登录token", required = true)
    private String token;

}
