package com.yht.demo.service;

import com.yht.demo.common.Result;

/**
 * <p>
 * 充值记录表 服务类
 * </p>
 *
 * @author yanht
 * @since 2019-04-20
 */
public interface IPayRecordService {

    Result getPayRecordList(String token, String clientName);
}
