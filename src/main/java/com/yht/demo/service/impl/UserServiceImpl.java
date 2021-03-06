package com.yht.demo.service.impl;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.yht.demo.common.*;
import com.yht.demo.common.utils.MD5Util;
import com.yht.demo.dto.*;
import com.yht.demo.entity.Client;
import com.yht.demo.entity.SmsConfig;
import com.yht.demo.entity.User;
import com.yht.demo.mapper.ClientMapper;
import com.yht.demo.mapper.NavigationTabMapper;
import com.yht.demo.mapper.SmsConfigMapper;
import com.yht.demo.mapper.UserMapper;
import com.yht.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * <p>
 * APP用户表 服务实现类
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NavigationTabMapper navigationTabMapper;
    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private SmsConfigMapper smsConfigMapper;

    @Override
    public Result sendVerificationCode(ParameterSendVerifyCode parameterSendVerifyCode) {
        try {
            SmsConfig smsConfig = smsConfigMapper.selectByclientName(parameterSendVerifyCode.getClientName());
            SMSUtils.sendVerifyLoginSMS(parameterSendVerifyCode.getMobileNo(), smsConfig);
            return Result.success("发送成功！");
        } catch (Exception e) {
            log.error("sendVerificationCode===========" + e.getMessage());
            return Result.error(500, "发送失败！");
        }
    }

    @Override
    public Result verifyCodeLoginOrRegister(ParameterUserDTO parameterUserDTO) {

        try {
            //获取验证码
            String localCode = stringRedisTemplate.opsForValue().get("SMS" + parameterUserDTO.getMobileNo());
            if (StringUtils.isEmpty(localCode) || !localCode.equals(parameterUserDTO.getCode())) {
                //return Result.error(500, "验证码错误！");
            }

            //redis保存token对应的userId(永久)
            String token = MD5Util.md5Encrypt32Upper(UUID.randomUUID().toString());
            Client client = clientMapper.selectClientByName(parameterUserDTO.getClientName());
            //数据库操作
            User user = userMapper.getUserInfo(parameterUserDTO.getMobileNo(), String.valueOf(client.getId()));
            if (user == null) {
                //保存用户信息
                User userNew = new User();
                userNew.setMobileNo(parameterUserDTO.getMobileNo());
                userNew.setClientName(parameterUserDTO.getClientName());
                userNew.setClientId(client.getId());
                userNew.setClientVersion(parameterUserDTO.getAppVersion());
                userNew.setRoleId(1);
                userNew.setStatus(0);
                userNew.setClientType(Integer.valueOf(parameterUserDTO.getClientType()));
                userNew.setCreateTime(new Date());
                userMapper.insert(userNew);

                //redis保存token对应的UserId(永久)
                RedisUtils.saveUserIdByToken(token, String.valueOf(userNew.getId()));
            } else {
                user.setClientVersion(parameterUserDTO.getAppVersion());
                user.setUpdateTime(new Date());
                userMapper.updateById(user);

                //redis保存token对应的UserId(永久)
                RedisUtils.saveUserIdByToken(token, String.valueOf(user.getId()));
            }

            String userIdByToken = RedisUtils.getUserIdByToken(token);
            ResultUserInfoDTO resultUserInfoDTO = userMapper.selectUserInfoById(userIdByToken);
            resultUserInfoDTO.setToken(token);
            return Result.success(resultUserInfoDTO);
        } catch (Exception e) {
            log.error("verifyCodeLoginOrRegister===========" + e.getMessage());
            return Result.error(500, "登录失败！");
        }

    }

    @Override
    public Result loginOut(ParameterBaseDTO parameterBaseDTO) {
        try {
            String mobileNo = stringRedisTemplate.opsForValue().get(parameterBaseDTO.getToken());
            stringRedisTemplate.delete("SMS" + mobileNo);
            stringRedisTemplate.delete(parameterBaseDTO.getToken());
            return Result.success("退出成功");
        } catch (Exception e) {
            log.error("loginOut===========" + e.getMessage());
            return Result.error(500, "退出失败！");
        }

    }

    @Override
    public Result getAppInfo(ParameterBaseDTO parameterBaseDTO) {
        Map<String, Object> parameterMap = new HashMap<>();
        //获取首页导航栏信息
        List<ResultNavigationTabDTO> resultNavigationTabDTOList = navigationTabMapper.getNavigationTabList(parameterBaseDTO.getClientId());
        parameterMap.put("navigationTabList", resultNavigationTabDTOList);
        return Result.success(parameterMap);

        //
    }

    @Override
    public Result getUserInfo(ParameterBaseDTO parameterBaseDTO) {

        //获取用户信息
        String userId = RedisUtils.getUserIdByToken(parameterBaseDTO.getToken());
        if (StringUtils.isEmpty(userId)) {
            return Result.error(500, MsgConstant.USER_ID_IS_NULL);
        }
        ResultUserInfoDTO resultUserInfoDTO = userMapper.selectUserInfoById(userId);
        return Result.success(resultUserInfoDTO);
    }

    @Override
    public Result getPortraitUploadCredentials(ParameterBaseDTO parameterBaseDTO) {

        String bucket = parameterBaseDTO.getClientName();
        // 密钥配置
        Auth auth = Auth.create(Constant.QINIU_ACCESS_KEY, Constant.QINIU_SECRET_KEY);
        // 要上传的空间
        if (bucket == null || "".equals(bucket.trim())) {
            bucket = Constant.QINIU_ICON_BUCKET;
        }
        //上传到七牛后保存的文件名
        String key = bucket + "userPortrait" + "_" + System.currentTimeMillis() + ".jpg";
        //上传到七牛云的token
        String token = auth.uploadToken(bucket, key, 300, new StringMap());
        Map<String, Object> map = new HashMap<>();
        map.put("UserPortraitToken", token);
        map.put("UserPortraitKey", key);
        return Result.success(map);
    }
}
