package com.yht.demo.controller;


import com.yht.demo.common.MsgConstant;
import com.yht.demo.common.Result;
import com.yht.demo.dto.ParameterAPPInfoDTO;
import com.yht.demo.dto.ParameterSendVerifyCode;
import com.yht.demo.dto.ParameterUserDTO;
import com.yht.demo.dto.ResultAPPInfoDTO;
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
        if (StringUtils.isEmpty(parameterSendVerifyCode.getMobileNo()) || StringUtils.isEmpty(parameterSendVerifyCode.getClientName())) {
            return Result.error(500, MsgConstant.PARAMETER_IS_NULL);
        }
        return userService.sendVerificationCode(parameterSendVerifyCode);
    }

    @PostMapping("/loginOrRegister")
    @ApiOperation(value = "验证码登录或注册")
    public Result loginOrRegister(@RequestBody ParameterUserDTO parameterUserDTO) {
        if (parameterUserDTO == null) {
            return Result.error(500, MsgConstant.PARAMETER_IS_NULL);
        }
        return userService.verifyCodeLoginOrRegister(parameterUserDTO);
    }

    @PostMapping("/loginOut")
    @ApiOperation(value = "退出登录")
    public Result loginOut(@RequestParam String token) {
        if (StringUtils.isEmpty(token)) {
            return Result.error(500, MsgConstant.PARAMETER_IS_NULL);
        }
        return userService.loginOut(token);
    }

    @PostMapping("/getAppInfo")
    @ApiOperation(value = "获取用户信息")
    public Result<ResultAPPInfoDTO> getAppInfo(@RequestBody ParameterAPPInfoDTO parameterAPPInfoDTO) {
        if (StringUtils.isEmpty(parameterAPPInfoDTO.getToken()) || StringUtils.isEmpty(parameterAPPInfoDTO.getClientName())) {
            return Result.error(500, MsgConstant.PARAMETER_IS_NULL);
        }
        return userService.getAppInfo(parameterAPPInfoDTO);
    }
}

