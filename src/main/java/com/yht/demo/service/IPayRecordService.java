package com.yht.demo.service;

import com.yht.demo.common.Result;
import com.yht.demo.entity.dto.ParameterPayRecordDTO;

/**
 * <p>
 * 充值记录表 服务类
 * </p>
 *
 * @author yanht
 * @since 2019-04-20
 */
public interface IPayRecordService {

    Result getPayRecordList(ParameterPayRecordDTO parameterPayRecordDTO);
}
