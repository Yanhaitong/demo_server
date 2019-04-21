package com.yht.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yht.demo.entity.dto.ResultOrderDetailsDTO;
import com.yht.demo.entity.model.OrderAllocation;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 抢单记录表 Mapper 接口
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
public interface OrderAllocationMapper extends BaseMapper<OrderAllocation> {

    IPage<ResultOrderDetailsDTO> amaldarOrderList(@Param("page") Page page, @Param("userId") Integer userId);
}
