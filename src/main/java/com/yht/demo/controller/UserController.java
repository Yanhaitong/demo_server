package com.yht.demo.controller;


import com.yht.demo.common.MsgConstant;
import com.yht.demo.common.Result;
import com.yht.demo.dto.*;
import com.yht.demo.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * APP用户表 前端控制器
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
@Api("用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /*@RequestMapping(value = "/captcha/{mark}", method = RequestMethod.GET)
    @ApiOperation(value = "获取图片验证码")
    public void captcha(@PathVariable String mark, HttpServletRequest request, HttpServletResponse response) {
        log.info("captcha:" + mark);
        try {
            if (mark == null || "".equals(mark.trim())) {
                return;
            }
            String verifyCode = CaptchaUtil.outputImage(response.getOutputStream());
            CacheUtil.saveCaptcha(mark + verifyCode.toLowerCase());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }*/

    @PostMapping("/sendVerifyCode")
    @ApiOperation(value = "发送验证码")
    public Result sendVerificationCode(@RequestBody ParameterSendVerifyCode parameterSendVerifyCode) {
        if (StringUtils.isEmpty(parameterSendVerifyCode.getMobileNo())) {
            return Result.error(500, MsgConstant.PARAMETER_IS_NULL);
        }
        return userService.sendVerificationCode(parameterSendVerifyCode);
    }

    @PostMapping("/loginOrRegister")
    @ApiOperation(value = "验证码登录或注册")
    public Result<ResultUserInfoDTO> loginOrRegister(@RequestBody ParameterUserDTO parameterUserDTO) {
        if (StringUtils.isEmpty(parameterUserDTO.getMobileNo()) || StringUtils.isEmpty(parameterUserDTO.getCode())) {
            return Result.error(500, MsgConstant.PARAMETER_IS_NULL);
        }
        return userService.verifyCodeLoginOrRegister(parameterUserDTO);
    }

    @PostMapping("/loginOut")
    @ApiOperation(value = "退出登录")
    public Result loginOut(@RequestBody ParameterBaseDTO parameterBaseDTO) {
        return userService.loginOut(parameterBaseDTO);
    }

    @PostMapping("/getAppInfo")
    @ApiOperation(value = "获取App初始化信息")
    public Result<ResultAPPInfoDTO> getAppInfo(@RequestBody ParameterBaseDTO parameterBaseDTO) {
        return userService.getAppInfo(parameterBaseDTO);
    }

    @PostMapping("/getUserInfo")
    @ApiOperation(value = "获取用户信息")
    public Result<ResultUserInfoDTO> getUserInfo(@RequestBody ParameterBaseDTO parameterBaseDTO) {
        return userService.getUserInfo(parameterBaseDTO);
    }

    @PostMapping("/getPortraitUploadCredentials")
    @ApiOperation(value = "获取用户头像上传凭证")
    public Result getPortraitUploadCredentials(@RequestBody ParameterBaseDTO parameterBaseDTO) {
        return userService.getPortraitUploadCredentials(parameterBaseDTO);
    }

}

