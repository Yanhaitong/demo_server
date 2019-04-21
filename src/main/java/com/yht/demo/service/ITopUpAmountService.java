package com.yht.demo.service;

import com.yht.demo.common.Result;

/**
 * <p>
 * 充值金额表 服务类
 * </p>
 *
 * @author yanht
 * @since 2019-04-20
 */
public interface ITopUpAmountService {

    Result topUpAmountInfo(String clientName);
}
