package com.yht.demo.controller;


import com.yht.demo.common.Result;
import com.yht.demo.service.IAmaldarCertificationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yht.demo.common.BaseController;

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

    @PostMapping("/loginOut")
    @ApiOperation(value = "获取经理认证信息")
    public Result getAmaldarCertificationInfo(HttpServletRequest request, HttpServletResponse response) {
        return amaldarCertificationService.getAmaldarCertificationInfo();
    }

    @PostMapping("/idCardValidation")
    @ApiOperation(value = "身份证OCR验证")
    public Result idCardValidation(HttpServletRequest request, HttpServletResponse response) {
        return amaldarCertificationService.idCardValidation();
    }

    @PostMapping("/getBizToken")
    @ApiOperation(value = "获取BizToken")
    public Result getBizToken(HttpServletRequest request, HttpServletResponse response) {
        return amaldarCertificationService.getBizToken();
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

