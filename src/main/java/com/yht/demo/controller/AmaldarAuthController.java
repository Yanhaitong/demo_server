package com.yht.demo.controller;


import com.yht.demo.common.MsgConstant;
import com.yht.demo.common.RedisUtils;
import com.yht.demo.common.Result;
import com.yht.demo.dto.*;
import com.yht.demo.service.IAmaldarAuthService;
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
@RequestMapping("/amaldarAuth")
public class AmaldarAuthController {

    @Autowired
    private IAmaldarAuthService amaldarAuthService;

    @PostMapping("/getAmaldarAuthInfo")
    @ApiOperation(value = "获取经理认证信息")
    public Result getAmaldarAuthInfo(@RequestBody ParameterUserInfoDTO parameterUserInfoDTO) {
        if (StringUtils.isEmpty(parameterUserInfoDTO.getToken())) {
            return Result.error(500, MsgConstant.PARAMETER_IS_NULL);
        }
        return amaldarAuthService.getAmaldarAuthInfo(parameterUserInfoDTO);
    }

    @PostMapping("/idCardValidation")
    @ApiOperation(value = "身份证OCR验证")
    public Result idCardValidation(@RequestBody ParameterIdCardDTO parameterIdCardDTO) {
        if (StringUtils.isEmpty(parameterIdCardDTO.getToken()) || StringUtils.isEmpty(parameterIdCardDTO.getIdCardSide()) ||
                StringUtils.isEmpty(parameterIdCardDTO.getFile())) {
            return Result.error(500, MsgConstant.PARAMETER_IS_NULL);
        }
        return amaldarAuthService.idCardValidation(parameterIdCardDTO);
    }

    @PostMapping("/getBizToken")
    @ApiOperation(value = "获取BizToken")
    public Result getBizToken(@RequestBody ParameterUserInfoDTO parameterUserInfoDTO) {
        if (StringUtils.isEmpty(parameterUserInfoDTO.getToken())) {
            return Result.error(500, MsgConstant.PARAMETER_IS_NULL);
        }
        return amaldarAuthService.getBizToken(parameterUserInfoDTO);
    }

    @PostMapping("/getVerifyResult")
    @ApiOperation(value = "获取活体识别结果")
    public Result getVerifyResult(@RequestBody ParameterUserInfoDTO parameterUserInfoDTO) {
        if (StringUtils.isEmpty(parameterUserInfoDTO.getToken())) {
            return Result.error(500, MsgConstant.PARAMETER_IS_NULL);
        }
        return amaldarAuthService.getVerifyResult(parameterUserInfoDTO);
    }

    @PostMapping("/companyAuth")
    @ApiOperation(value = "公司认证")
    public Result companyAuth(@RequestBody ParameterUserInfoDTO parameterUserInfoDTO) {

        /*User user = userService.getUserByMobileNo(mobileNo, 2, client);
        AmaldarCertification amaldarCertification = this.amaldarCertificationMapper.getCertificationInfo(user.getId());
        if ( null != amaldarCertification && amaldarCertification.getStatus() == 2){
            amaldarCertification.setStatus(3);
            amaldarCertificationMapper.updateByPrimaryKeySelective(amaldarCertification);
        }

        //修改经理状态status为1待审核
        user.setStatus(1);
        userService.updateUser(user);

        JSONObject mapData = new JSONObject();
        mapData.put("faceStatus", user.getFaceStatus());
        returnObj.put("code", 200);
        returnObj.put("data", JSONObject.fromObject(mapData, jsonConfig));*/

        String userId = RedisUtils.getUserIdByToken(parameterUserInfoDTO.getToken());

        return amaldarAuthService.companyAuth(parameterUserInfoDTO);
    }

    @PostMapping("/getCompanyUploadCredentials")
    @ApiOperation(value = "获取公司认证上传凭证")
    public Result<ResultQiNiuCredentialsDTO> getUploadCredentials(@RequestBody ParameterBaseDTO parameterBaseDTO) {
        return amaldarAuthService.getUploadCredentials(parameterBaseDTO);
    }
}

