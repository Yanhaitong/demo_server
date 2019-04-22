package com.yht.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yht.demo.dto.ResultPayRecordDTO;
import com.yht.demo.entity.PayRecord;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 充值记录表 Mapper 接口
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
public interface PayRecordMapper extends BaseMapper<PayRecord> {

    IPage<ResultPayRecordDTO> getPayRecordList(@Param("page") Page page, @Param("userId") String userId, @Param("clientId") String clientId);
}
