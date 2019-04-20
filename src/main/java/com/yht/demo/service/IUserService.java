package com.yht.demo.service;

import com.yht.demo.common.Result;
import com.yht.demo.entity.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yht.demo.entity.dto.UserReceiveDTO;

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

    Result verifyCodeLoginOrRegister(UserReceiveDTO userReceiveDTO);

    Result loginOut(String mobileNo);

    Result getAppInfo(String token, String clientName);
}
