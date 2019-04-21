package com.yht.demo.service.impl;

import com.yht.demo.common.BaseServiceImpl;
import com.yht.demo.common.MsgConstant;
import com.yht.demo.common.RedisUtils;
import com.yht.demo.common.Result;
import com.yht.demo.entity.dto.ParameterBaseDTO;
import com.yht.demo.entity.model.PayRecord;
import com.yht.demo.mapper.PayRecordMapper;
import com.yht.demo.service.IPayRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 充值记录表 服务实现类
 * </p>
 *
 * @author yanht
 * @since 2019-04-20
 */
@Service
public class PayRecordServiceImpl extends BaseServiceImpl implements IPayRecordService {

    @Autowired
    private PayRecordMapper payRecordMapper;

    @Override
    public Result getPayRecordList(ParameterBaseDTO parameterBaseDTO) {
        String mobileNo = RedisUtils.getMobileByToken(parameterBaseDTO.getToken());
        if (StringUtils.isEmpty(mobileNo)){
            return Result.error(500, MsgConstant.MOBILE_NO_IS_NULL);
        }

        List<PayRecord> payRecordList = payRecordMapper.getPayRecordList(mobileNo, parameterBaseDTO.getClientName());
        return Result.success(payRecordList);
    }
}
