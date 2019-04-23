package com.yht.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yanht
 * @since 2019-04-19
 */
@ApiModel(value = "返回七牛上传凭证参数")
@Data
public class ResultQiNiuCredentialsDTO {

    @ApiModelProperty(name = "companyLogoToken", value = "与公司LOGO合影照片上传Token", required = true)
    private String companyLogoToken;

    @ApiModelProperty(name = "companyWorkCardToken", value = "公司工牌或名片上传Token", required = true)
    private String companyWorkCardToken;

    @ApiModelProperty(name = "companyLicenseToken", value = "公司营业执照上传Token", required = true)
    private String companyLicenseToken;

    @ApiModelProperty(name = "laborContractToken", value = "公司劳动合同上传Token", required = true)
    private String laborContractToken;

    @ApiModelProperty(name = "companyLogoKey", value = "与公司LOGO合影照片上传文件名", required = true)
    private String companyLogoKey;

    @ApiModelProperty(name = "companyWorkCardKey", value = "公司工牌或名片上传文件名", required = true)
    private String companyWorkCardKey;

    @ApiModelProperty(name = "companyLicenseKey", value = "公司营业执照上传文件名", required = true)
    private String companyLicenseKey;

    @ApiModelProperty(name = "laborContractKey", value = "公司劳动合同上传文件名", required = true)
    private String laborContractKey;
}