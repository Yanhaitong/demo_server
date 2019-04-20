package com.yht.demo.mapper;

import com.yht.demo.entity.model.PayRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 充值记录表 Mapper 接口
 * </p>
 *
 * @author yanht
 * @since 2019-04-20
 */
public interface PayRecordMapper extends BaseMapper<PayRecord> {

    List<PayRecord> getPayRecordList(String mobileNo, String clientName);
}
