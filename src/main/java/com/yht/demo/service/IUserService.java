package com.yht.demo.service;

import com.yht.demo.common.Result;
import com.yht.demo.entity.dto.ParameterUserDTO;
import com.yht.demo.entity.dto.ParameterBaseDTO;

/**
 * <p>
 * APP用户表 服务类
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
public interface IUserService {

    Result sendVerificationCode(String mobileNo, String clientName);

    Result verifyCodeLoginOrRegister(ParameterUserDTO parameterUserDTO);

    Result loginOut(String mobileNo);

    Result getAppInfo(ParameterBaseDTO parameterBaseDTO);

}
