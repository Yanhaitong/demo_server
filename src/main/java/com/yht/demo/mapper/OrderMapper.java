package com.yht.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yht.demo.entity.dto.ResultOrderDetailsDTO;
import com.yht.demo.entity.dto.ParameterOrderListDTO;
import com.yht.demo.entity.model.Order;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
public interface OrderMapper extends BaseMapper<Order> {

    IPage<ResultOrderDetailsDTO> selectOrderListByMap(@Param("page") Page page, @Param("parameterOrderListDTO") ParameterOrderListDTO parameterOrderListDTO);

    ResultOrderDetailsDTO getOrderDetailsById(String orderId);

}