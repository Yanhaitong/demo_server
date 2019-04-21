package com.yht.demo.service;

import com.yht.demo.common.Result;
import com.yht.demo.entity.dto.ParameterAmaldarOrderListDTO;
import com.yht.demo.entity.dto.ParameterVieForOrderDTO;

/**
 * <p>
 * 抢单记录表 服务类
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
public interface IOrderAllocationService {

    Result vieForOrder(ParameterVieForOrderDTO parameterVieForOrderDTO);

    Result amaldarOrderList(ParameterAmaldarOrderListDTO parameterAmaldarOrderListDTO);
}
