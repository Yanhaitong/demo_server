package com.yht.demo.controller;


import com.yht.demo.common.MsgConstant;
import com.yht.demo.common.Result;
import com.yht.demo.dto.*;
import com.yht.demo.service.IAmaldarCertificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 经理face++认证表 前端控制器
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
@Api("身份认证信息管理")
@RestController
@RequestMapping("/amaldarCertification")
public class AmaldarCertificationController {

    @Autowired
    private IAmaldarCertificationService amaldarCertificationService;

    @PostMapping("/getAmaldarCertificationInfo")
    @ApiOperation(value = "获取经理认证信息")
    public Result getAmaldarCertificationInfo(@RequestBody ParameterUserInfoDTO parameterUserInfoDTO) {
        if (StringUtils.isEmpty(parameterUserInfoDTO.getToken())) {
            return Result.error(500, MsgConstant.PARAMETER_IS_NULL);
        }
        return amaldarCertificationService.getAmaldarCertificationInfo(parameterUserInfoDTO);
    }

    @PostMapping("/idCardValidation")
    @ApiOperation(value = "身份证OCR验证")
    public Result idCardValidation(@RequestBody ParameterIdCardDTO parameterIdCardDTO) {
        if (StringUtils.isEmpty(parameterIdCardDTO.getToken()) || StringUtils.isEmpty(parameterIdCardDTO.getIdCardSide()) ||
                StringUtils.isEmpty(parameterIdCardDTO.getFile())) {
            return Result.error(500, MsgConstant.PARAMETER_IS_NULL);
        }
        return amaldarCertificationService.idCardValidation(parameterIdCardDTO);
    }

    @PostMapping("/getBizToken")
    @ApiOperation(value = "获取BizToken")
    public Result getBizToken(@RequestBody ParameterUserInfoDTO parameterUserInfoDTO) {
        if (StringUtils.isEmpty(parameterUserInfoDTO.getToken())) {
            return Result.error(500, MsgConstant.PARAMETER_IS_NULL);
        }
        return amaldarCertificationService.getBizToken(parameterUserInfoDTO);
    }

    @PostMapping("/getVerifyResult")
    @ApiOperation(value = "获取活体识别结果")
    public Result getVerifyResult(@RequestBody ParameterUserInfoDTO parameterUserInfoDTO) {
        if (StringUtils.isEmpty(parameterUserInfoDTO.getToken())) {
            return Result.error(500, MsgConstant.PARAMETER_IS_NULL);
        }
        return amaldarCertificationService.getVerifyResult(parameterUserInfoDTO);
    }

    @PostMapping("/companyCertification")
    @ApiOperation(value = "公司认证")
    public Result companyCertification(@RequestBody ParameterBaseDTO parameterBaseDTO) {
        return amaldarCertificationService.companyCertification(parameterBaseDTO);
    }

    @PostMapping("/getCompanyUploadCredentials")
    @ApiOperation(value = "获取公司认证上传凭证")
    public Result<ResultQiNiuCredentialsDTO> getUploadCredentials(@RequestBody ParameterBaseDTO parameterBaseDTO) {
        return amaldarCertificationService.getUploadCredentials(parameterBaseDTO);
    }
}

