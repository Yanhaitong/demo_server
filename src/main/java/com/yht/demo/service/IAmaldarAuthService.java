package com.yht.demo.service;

import com.yht.demo.common.Result;
import com.yht.demo.dto.ParameterBaseDTO;
import com.yht.demo.dto.ParameterIdCardDTO;

/**
 * <p>
 * 经理face++认证表 服务类
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
public interface IAmaldarAuthService {

    Result getAmaldarAuthInfo(ParameterBaseDTO parameterBaseDTO);

    Result idCardValidation(ParameterIdCardDTO parameterIdCardDTO);

    Result getBizToken(ParameterBaseDTO parameterBaseDTO);

    Result getVerifyResult(ParameterBaseDTO parameterBaseDTO);

    Result companyAuth(ParameterBaseDTO parameterBaseDTO);

    Result getUploadCredentials(ParameterBaseDTO parameterBaseDTO);

}
