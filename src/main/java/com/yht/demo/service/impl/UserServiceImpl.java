package com.yht.demo.service.impl;

import com.yht.demo.common.BaseServiceImpl;
import com.yht.demo.common.MsgConstant;
import com.yht.demo.common.RedisUtils;
import com.yht.demo.common.Result;
import com.yht.demo.common.sender.SMSUtils;
import com.yht.demo.common.utils.MD5Util;
import com.yht.demo.entity.dto.ParameterBaseDTO;
import com.yht.demo.entity.dto.ResultNavigationTabDTO;
import com.yht.demo.entity.dto.ResultSearchConditionsDTO;
import com.yht.demo.entity.dto.ParameterUserDTO;
import com.yht.demo.entity.model.User;
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
 * @author yanht
 * @since 2019-04-19
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SystemConfigMapper systemConfigMapper;
    @Autowired
    private NavigationTabMapper navigationTabMapper;
    @Autowired
    private SearchConditionsMapper searchConditionsMapper;
    @Autowired
    private CityMapper cityMapper;

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
    public Result verifyCodeLoginOrRegister(ParameterUserDTO parameterUserDTO) {

        try {
            if (StringUtils.isEmpty(parameterUserDTO.getMobileNo()) || StringUtils.isEmpty(parameterUserDTO.getClientName()) ||
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
            User user = userMapper.getUserInfo(parameterUserDTO.getMobileNo(), parameterUserDTO.getClientName());
            if (user == null) {
                //保存用户信息
                User userNew = new User();
                userNew.setMobileNo(parameterUserDTO.getMobileNo());
                userNew.setClientName(parameterUserDTO.getClientName());
                userNew.setClientVersion(parameterUserDTO.getVersion());
                userNew.setCreateTime(new Date());
                userNew.setRoleId(1);
                userNew.setStatus(0);
                userNew.setClientType(Integer.valueOf(parameterUserDTO.getClientType()));
                userMapper.insert(userNew);

                //redis保存token对应的UserId(永久)
                RedisUtils.saveUserIdByToken(token, parameterUserDTO.getMobileNo());
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

    @Override
    public Result getAppInfo(ParameterBaseDTO parameterBaseDTO) {
        Map<String, Object> parameterMap = new HashMap<>();

        //获取用户信息
        String userId = RedisUtils.getUserIdByToken(parameterBaseDTO.getToken());
        if (StringUtils.isEmpty(userId)){
            return Result.error(500, MsgConstant.USER_ID_IS_NULL);
        }
        User userInfo = userMapper.selectById(userId);
        if (userInfo == null){
            return Result.error(500, MsgConstant.USER_INFO_IS_NULL);
        }
        parameterMap.put("userInfo", userInfo);

        //城市列表
        //List<String> cityList = cityMapper.selectAllCityList();
        //parameterMap.put("cityList", cityList);

        //获取首页导航栏信息
        List<ResultNavigationTabDTO> resultNavigationTabDTOList = navigationTabMapper.getNavigationTabList(parameterBaseDTO.getClientName());
        parameterMap.put("navigationTabList", resultNavigationTabDTOList);

        //获取搜索条件信息
        List<ResultSearchConditionsDTO> resultSearchConditionsDTOList = searchConditionsMapper.getSearchConditionsList(parameterBaseDTO.getClientName());
        parameterMap.put("searchConditionsList", resultSearchConditionsDTOList);

        return Result.success(parameterMap);
    }
}
