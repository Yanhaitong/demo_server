package com.yht.demo.service;

import com.yht.demo.common.Result;
import com.yht.demo.dto.ParameterBaseDTO;
import com.yht.demo.dto.ParameterIdCardDTO;
import com.yht.demo.dto.ParameterUserInfoDTO;

/**
 * <p>
 * 经理face++认证表 服务类
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
public interface IAmaldarAuthService {

    Result getAmaldarAuthInfo(ParameterUserInfoDTO parameterUserInfoDTO);

    Result idCardValidation(ParameterIdCardDTO parameterIdCardDTO);

    Result getBizToken(ParameterUserInfoDTO parameterUserInfoDTO);

    Result getVerifyResult(ParameterUserInfoDTO parameterUserInfoDTO);

    Result companyAuth(ParameterBaseDTO parameterBaseDTO);

    Result getUploadCredentials(ParameterBaseDTO parameterBaseDTO);

}
