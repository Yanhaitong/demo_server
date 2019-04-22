package com.yht.demo.service;

import com.yht.demo.common.Result;
import com.yht.demo.dto.ParameterAPPInfoDTO;
import com.yht.demo.dto.ParameterUserDTO;
import com.yht.demo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * APP用户表 服务类
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
public interface IUserService {

    Result sendVerificationCode(String mobileNo, String clientName);

    Result verifyCodeLoginOrRegister(ParameterUserDTO parameterUserDTO);

    Result loginOut(String mobileNo);

    Result getAppInfo(ParameterAPPInfoDTO parameterAPPInfoDTO);
}
