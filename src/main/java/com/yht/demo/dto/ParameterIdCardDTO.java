package com.yht.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yanht
 * @since 2019-04-19
 */
@ApiModel(value = "身份证OCR认证参数")
@Data
public class ParameterIdCardDTO {

    @ApiModelProperty(name = "token", value = "用户登录token", required = true)
    private String token;

    @ApiModelProperty(name = "idCardSide", value = "身份证正反面（0:国徽面，1:人像面）", required = true)
    private String idCardSide;

    @ApiModelProperty(name = "file", value = "扫描的身份证照片", required = true)
    private MultipartFile file;

}
