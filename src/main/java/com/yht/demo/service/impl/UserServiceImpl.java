package com.yht.demo.service.impl;

import com.yht.demo.common.BaseServiceImpl;
import com.yht.demo.common.MsgConstant;
import com.yht.demo.common.RedisUtils;
import com.yht.demo.common.Result;
import com.yht.demo.common.sender.SMSUtils;
import com.yht.demo.common.utils.MD5Util;
import com.yht.demo.dto.*;
import com.yht.demo.entity.Client;
import com.yht.demo.entity.User;
import com.yht.demo.mapper.*;
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
    private SmsConfigMapper smsConfigMapper;
    @Autowired
    private NavigationTabMapper navigationTabMapper;
    @Autowired
    private SearchConditionsMapper searchConditionsMapper;
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private ClientMapper clientMapper;

    @Override
    public Result sendVerificationCode(ParameterSendVerifyCode parameterSendVerifyCode) {
        try {
            String smsContent = smsConfigMapper.getValueByKey("SMS" + parameterSendVerifyCode.getClientId());
            SMSUtils.sendVerifyLoginSMS(parameterSendVerifyCode.getMobileNo(), smsContent);
            return Result.success("发送成功");
        } catch (Exception e) {
            log.error("sendVerificationCode===========" + e.getMessage());
            return Result.error(500, "发送失败");
        }
    }

    @Override
    public Result verifyCodeLoginOrRegister(ParameterUserDTO parameterUserDTO) {

        try {
            if (StringUtils.isEmpty(parameterUserDTO.getMobileNo()) || StringUtils.isEmpty(parameterUserDTO.getClientId()) ||
                    StringUtils.isEmpty(parameterUserDTO.getCode()) || StringUtils.isEmpty(parameterUserDTO.getClientType())){
                return Result.error(500, MsgConstant.PARAMETER_IS_NULL);
            }

            //获取验证码
            String localCode = stringRedisTemplate.opsForValue().get("SMS" + parameterUserDTO.getMobileNo());
            if (StringUtils.isEmpty(localCode) || !localCode.equals(parameterUserDTO.getCode())) {
                //return Result.error(500, "验证码错误！");
            }

            //redis保存token对应的UserId(永久)
            String token = MD5Util.md5Encrypt32Upper(UUID.randomUUID().toString());
            //数据库操作
            User user = userMapper.getUserInfo(parameterUserDTO.getMobileNo(), parameterUserDTO.getClientId());
            if (user == null) {
                //保存用户信息
                User userNew = new User();
                userNew.setMobileNo(parameterUserDTO.getMobileNo());
                Client client = clientMapper.selectById(parameterUserDTO.getClientId());
                userNew.setClientName(client.getName());
                userNew.setClientId(parameterUserDTO.getClientId());
                userNew.setClientVersion(parameterUserDTO.getVersion());
                userNew.setRoleId(1);
                userNew.setStatus(0);
                userNew.setClientType(Integer.valueOf(parameterUserDTO.getClientType()));
                userNew.setCreateTime(new Date());
                userMapper.insert(userNew);

                //redis保存token对应的UserId(永久)
                RedisUtils.saveUserIdByToken(token, String.valueOf(userNew.getId()));
            } else {
                user.setClientVersion(parameterUserDTO.getVersion());
                user.setUpdateTime(new Date());
                userMapper.updateById(user);

                //redis保存token对应的UserId(永久)
                RedisUtils.saveUserIdByToken(token, String.valueOf(user.getId()));
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
    public Result loginOut(ParameterUserInfoDTO parameterAPPInfoDTO) {
        try {
            String mobileNo = stringRedisTemplate.opsForValue().get(parameterAPPInfoDTO.getToken());
            stringRedisTemplate.delete("SMS" + mobileNo);
            stringRedisTemplate.delete(parameterAPPInfoDTO.getToken());
            return Result.success("退出成功");
        } catch (Exception e) {
            log.error("loginOut===========" + e.getMessage());
            return Result.error(500, "退出失败");
        }

    }

    @Override
    public Result getAppInfo(ParameterBase parameterBase) {
        Map<String, Object> parameterMap = new HashMap<>();

        //城市列表
        List<String> cityList = cityMapper.selectAllCityList();
        parameterMap.put("cityList", cityList);

        //获取首页导航栏信息
        List<ResultNavigationTabDTO> resultNavigationTabDTOList = navigationTabMapper.getNavigationTabList(parameterBase.getClientId());
        parameterMap.put("navigationTabList", resultNavigationTabDTOList);

        //获取搜索条件信息
        List<ResultSearchConditionsDTO> resultSearchConditionsDTOList = searchConditionsMapper.getSearchConditionsList(parameterBase.getClientId());
        parameterMap.put("searchConditionsList", resultSearchConditionsDTOList);

        return Result.success(parameterMap);
    }

    @Override
    public Result getUserInfo(ParameterUserInfoDTO parameterUserInfoDTO) {

        //获取用户信息
        String userId = RedisUtils.getUserIdByToken(parameterUserInfoDTO.getToken());
        if (StringUtils.isEmpty(userId)){
            return Result.error(500, MsgConstant.USER_ID_IS_NULL);
        }
        ResultUserInfoDTO resultUserInfoDTO = userMapper.selectUserInfoById(userId);
        if (resultUserInfoDTO == null){
            return Result.error(500, MsgConstant.USER_INFO_IS_NULL);
        }

        return Result.success(resultUserInfoDTO);
    }
}
