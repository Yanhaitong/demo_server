package com.yht.demo.controller;


import com.yht.demo.common.Result;
import com.yht.demo.common.utils.CacheUtil;
import com.yht.demo.common.utils.CaptchaUtil;
import com.yht.demo.entity.dto.UserReceiveDTO;
import com.yht.demo.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.yht.demo.common.BaseController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * APP用户表 前端控制器
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/captcha/{mark}", method = RequestMethod.GET)
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
    }

    @PostMapping("/sendVerifyCode")
    @ApiOperation(value = "发送验证码")
    public Result sendVerificationCode(@RequestParam String mobileNo, @RequestParam String clientName) {
        if (StringUtils.isEmpty(mobileNo) || StringUtils.isEmpty(clientName)) {
            return Result.error(500, "发送失败，参数错误！");
        }
        return userService.sendVerificationCode(mobileNo, clientName);
    }

    @PostMapping("/loginOrRegister")
    @ApiOperation(value = "验证码登录或注册")
    public Result loginOrRegister(@ModelAttribute UserReceiveDTO userReceiveDTO) {
        if (StringUtils.isEmpty(userReceiveDTO.getMobileNo()) || StringUtils.isEmpty(userReceiveDTO.getCode())) {
            return Result.error(500, "退出失败，参数错误！");
        }

        return userService.verifyCodeLoginOrRegister(userReceiveDTO);
    }

    @PostMapping("/loginOut")
    @ApiOperation(value = "退出登录")
    public Result loginOut(@RequestParam String token) {
        if (StringUtils.isEmpty(token)) {
            return Result.error(500, "退出失败，参数错误！");
        }
        return userService.loginOut(token);
    }
}

