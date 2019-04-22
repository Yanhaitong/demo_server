package com.yht.demo.service;

import com.yht.demo.common.Result;
import com.yht.demo.dto.ParameterPayRecordDTO;
import com.yht.demo.entity.PayRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 充值记录表 服务类
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
public interface IPayRecordService {

    Result getPayRecordList(ParameterPayRecordDTO parameterPayRecordDTO);
}
