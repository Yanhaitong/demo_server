package com.yht.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "查询用户流水信息的参数")
@Data
public class ParameterPayRecordDTO {

    @ApiModelProperty(name = "token", value = "用户登录token", required = true)
    private String token;

    @ApiModelProperty(name = "clientName", value = "客户端名称", required = true)
    private String clientName;

    @ApiModelProperty(name = "pageSize", value = "每页个数", required = true)
    private Integer pageSize;

    @ApiModelProperty(name = "pageNum", value = "页数", required = true)
    private Integer pageNum;
}
