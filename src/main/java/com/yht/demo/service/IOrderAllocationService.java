package com.yht.demo.service;

import com.yht.demo.common.Result;
import com.yht.demo.dto.ParameterAmaldarOrderListDTO;
import com.yht.demo.dto.ParameterVieForOrderDTO;

/**
 * <p>
 * 抢单记录表 服务类
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
public interface IOrderAllocationService {

    Result vieForOrder(ParameterVieForOrderDTO parameterVieForOrderDTO);

    Result amaldarOrderList(ParameterAmaldarOrderListDTO parameterAmaldarOrderListDTO);
}
