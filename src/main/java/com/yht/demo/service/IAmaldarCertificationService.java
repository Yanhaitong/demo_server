package com.yht.demo.service;

import com.yht.demo.common.Result;
import com.yht.demo.entity.AmaldarCertification;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 经理face++认证表 服务类
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
public interface IAmaldarCertificationService {

    Result getAmaldarCertificationInfo();

    Result idCardValidation();

    Result getBizToken();

    Result companyCertification();

    Result getUploadCredentials();
}
