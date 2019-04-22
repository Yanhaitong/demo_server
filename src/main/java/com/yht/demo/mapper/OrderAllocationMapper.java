package com.yht.demo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yht.demo.dto.ResultOrderDetailsDTO;
import com.yht.demo.entity.OrderAllocation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 抢单记录表 Mapper 接口
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
public interface OrderAllocationMapper extends BaseMapper<OrderAllocation> {

    IPage<ResultOrderDetailsDTO> amaldarOrderList(@Param("page") Page page, @Param("userId") Integer userId);
}
