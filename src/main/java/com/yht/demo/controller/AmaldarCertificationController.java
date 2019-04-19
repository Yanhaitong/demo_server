package com.yht.demo.controller;


import com.yht.demo.common.Result;
import com.yht.demo.entity.AmaldarCertification;
import com.yht.demo.entity.dto.AmaldarCertificationReceiveDTO;
import com.yht.demo.service.IAmaldarCertificationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.yht.demo.common.BaseController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 经理face++认证表 前端控制器
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
@RestController
@RequestMapping("/amaldarCertification")
public class AmaldarCertificationController extends BaseController {

    @Autowired
    private IAmaldarCertificationService amaldarCertificationService;

    @PostMapping("/getAmaldarCertificationInfo")
    @ApiOperation(value = "获取经理认证信息")
    public Result getAmaldarCertificationInfo(String token, String client) {
        return amaldarCertificationService.getAmaldarCertificationInfo(token, client);
    }

    @PostMapping("/idCardValidation")
    @ApiOperation(value = "身份证OCR验证")
    public Result idCardValidation(String token, String client, String idCardSide, MultipartFile file) {
        return amaldarCertificationService.idCardValidation(token, client, idCardSide, file);
    }

    @PostMapping("/getBizToken")
    @ApiOperation(value = "获取BizToken")
    public Result getBizToken(String token, String client) {
        return amaldarCertificationService.getBizToken(token, client);
    }

    @PostMapping("/getVerifyResult")
    @ApiOperation(value = "获取活体识别结果")
    public Result getVerifyResult(String token, String client) {
        return amaldarCertificationService.getVerifyResult(token, client);
    }

    @PostMapping("/companyCertification")
    @ApiOperation(value = "公司认证")
    public Result companyCertification(HttpServletRequest request, HttpServletResponse response) {
        return amaldarCertificationService.companyCertification();
    }

    @PostMapping("/getUploadCredentials")
    @ApiOperation(value = "获取上传凭证")
    public Result getUploadCredentials(HttpServletRequest request, HttpServletResponse response) {
        return amaldarCertificationService.getUploadCredentials();
    }
}

