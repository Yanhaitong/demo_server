package com.yht.demo.service.impl;

import com.yht.demo.common.BaseService;
import com.yht.demo.common.Result;
import com.yht.demo.common.sender.SMSUtils;
import com.yht.demo.common.utils.MD5Util;
import com.yht.demo.entity.dto.UserReceiveDTO;
import com.yht.demo.entity.model.User;
import com.yht.demo.mapper.SystemConfigMapper;
import com.yht.demo.mapper.UserMapper;
import com.yht.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * APP用户表 服务实现类
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
@Service
public class UserServiceImpl extends BaseService implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SystemConfigMapper systemConfigMapper;

    @Override
    public Result sendVerificationCode(String mobileNo, String clientName) {
        try {
            String smsContent = systemConfigMapper.getValueByKey("SMS" + clientName);
            SMSUtils.sendVerifyLoginSMS(mobileNo, smsContent);
            return Result.success("发送成功");
        } catch (Exception e) {
            log.error("sendVerificationCode===========" + e.getMessage());
            return Result.error(500, "发送失败");
        }
    }

    @Override
    public Result verifyCodeLoginOrRegister(UserReceiveDTO userReceiveDTO) {

        try {
            //获取验证码
            String localCode = stringRedisTemplate.opsForValue().get("SMS" + userReceiveDTO.getMobileNo());
            if (StringUtils.isEmpty(localCode) || !localCode.equals(userReceiveDTO.getCode())) {
                return Result.error(500, "验证码错误！");
            }

            //redis保存token对应的手机号(永久)
            String token = MD5Util.md5Encrypt32Upper(UUID.randomUUID().toString());
            stringRedisTemplate.opsForValue().set(token, userReceiveDTO.getMobileNo());

            //数据库操作
            User user = userMapper.getUserInfo(userReceiveDTO.getMobileNo(), userReceiveDTO.getClientName());
            if (user == null) {
                //保存用户信息
                User userNew = new User();
                userNew.setMobileNo(userReceiveDTO.getMobileNo());
                userNew.setClientName(userReceiveDTO.getClientName());
                userNew.setClientVersion(userReceiveDTO.getVersion());
                userNew.setCreateTime(new Date());
                userNew.setClientType(userReceiveDTO.getClientType());
                userMapper.insert(userNew);
            } else {
                user.setClientVersion(userReceiveDTO.getVersion());
                user.setUpdateTime(new Date());
                userMapper.updateById(user);
            }
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            return Result.success(map);
        } catch (Exception e) {
            log.error("verifyCodeLoginOrRegister===========" + e.getMessage());
            return Result.error(500, "登录失败");
        }

    }

    @Override
    public Result loginOut(String token) {
        try {
            String mobileNo = stringRedisTemplate.opsForValue().get(token);
            stringRedisTemplate.delete("SMS" + mobileNo);
            stringRedisTemplate.delete(token);
            return Result.success("退出成功");
        } catch (Exception e) {
            log.error("loginOut===========" + e.getMessage());
            return Result.error(500, "退出失败");
        }

    }
}
