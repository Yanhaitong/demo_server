package com.yht.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "接收经理认证的参数")
@Data
public class ParameterAmaldarCertificationDTO extends ParameterBase{

    @ApiModelProperty(name = "mobileNo", value = "手机号", required = true)
    private String mobileNo;
    
    @ApiModelProperty(name = "code", value = "验证码", required = true)
    private String code;

}
