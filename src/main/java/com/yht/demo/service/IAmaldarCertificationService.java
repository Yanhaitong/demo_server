package com.yht.demo.service;

import com.yht.demo.common.Result;
import com.yht.demo.dto.ParameterBaseDTO;
import com.yht.demo.dto.ParameterIdCardDTO;
import com.yht.demo.dto.ParameterUserInfoDTO;
import com.yht.demo.entity.AmaldarCertification;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 经理face++认证表 服务类
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
public interface IAmaldarCertificationService {

    Result getAmaldarCertificationInfo(ParameterUserInfoDTO parameterUserInfoDTO);

    Result idCardValidation(ParameterIdCardDTO parameterIdCardDTO);

    Result getBizToken(ParameterUserInfoDTO parameterUserInfoDTO);

    Result getVerifyResult(ParameterUserInfoDTO parameterUserInfoDTO);

    Result companyCertification(ParameterBaseDTO parameterBaseDTO);

    Result getUploadCredentials(ParameterBaseDTO parameterBaseDTO);

}
