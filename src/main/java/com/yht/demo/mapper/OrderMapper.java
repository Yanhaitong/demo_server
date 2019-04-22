package com.yht.demo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yht.demo.dto.ParameterOrderListDTO;
import com.yht.demo.dto.ResultOrderDetailsDTO;
import com.yht.demo.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
public interface OrderMapper extends BaseMapper<Order> {

    IPage<ResultOrderDetailsDTO> selectOrderListByMap(@Param("page") Page page, @Param("parameterOrderListDTO") ParameterOrderListDTO parameterOrderListDTO);

    ResultOrderDetailsDTO getOrderDetailsById(@Param("orderId") String orderId, @Param("myOrderInt") Integer myOrderInt);
}
