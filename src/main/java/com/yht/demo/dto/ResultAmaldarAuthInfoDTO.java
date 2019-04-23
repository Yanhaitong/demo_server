package com.yht.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yanht
 * @since 2019-04-19
 */
@ApiModel(value = "返回经理认证信息参数")
@Data
public class ResultAmaldarAuthInfoDTO {

    @ApiModelProperty(name = "status", value = "经理认证状态（0:未开始，1:身份已认证，2:人脸已认证，3:公司已认证）", required = true)
    private String status;

    @ApiModelProperty(name = "workCity", value = "工作城市", required = true)
    private String workCity;

    @ApiModelProperty(name = "companyLogoUrl", value = "与公司LOGO合影照片URL", required = true)
    private String companyLogoUrl;

    @ApiModelProperty(name = "companyWorkCardUrl", value = "公司工牌或名片URL", required = true)
    private String companyWorkCardUrl;

    @ApiModelProperty(name = "companyLicenseUrl", value = "公司营业执照URL", required = true)
    private String companyLicenseUrl;

    @ApiModelProperty(name = "laborContractUrl", value = "公司劳动合同URL", required = true)
    private String laborContractUrl;

}