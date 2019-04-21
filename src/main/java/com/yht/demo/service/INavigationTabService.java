package com.yht.demo.service;

import com.yht.demo.common.Result;
import com.yht.demo.entity.dto.ParameterOrderListDTO;

/**
 * <p>
 * app首页导航选项卡 服务类
 * </p>
 *
 * @author yanht
 * @since 2019-04-20
 */
public interface INavigationTabService {

    Result getHomePageNavigationList(ParameterOrderListDTO parameterOrderListDTO);
}
