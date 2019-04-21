package com.yht.demo.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
@ApiModel(value = "查询用户相关信息的参数")
@Data
public class ParameterUserInfoDTO {

    @ApiModelProperty(name = "token", value = "用户登录token", required = true)
    private String token;

    @ApiModelProperty(name = "clientName", value = "客户端名称", required = true)
    private String clientName;

}