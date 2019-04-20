package com.yht.demo.service;

import com.yht.demo.common.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 经理face++认证表 服务类
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
public interface IAmaldarCertificationService {

    Result getAmaldarCertificationInfo(String token, String client);

    Result idCardValidation(String token, String client, String idCardSide, MultipartFile file);

    Result getBizToken(String token, String client);

    Result getVerifyResult(String token, String client);

    Result companyCertification();

    Result getUploadCredentials();

}