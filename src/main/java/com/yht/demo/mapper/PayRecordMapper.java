package com.yht.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yht.demo.entity.dto.ResultPayRecordDTO;
import com.yht.demo.entity.model.PayRecord;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 充值记录表 Mapper 接口
 * </p>
 *
 * @author yanht
 * @since 2019-04-20
 */
public interface PayRecordMapper extends BaseMapper<PayRecord> {

    IPage<ResultPayRecordDTO> getPayRecordList(@Param("page") Page page, @Param("userId") String userId);
}
