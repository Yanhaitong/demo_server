package com.yht.demo.service;

import com.yht.demo.common.Result;
import com.yht.demo.dto.ParameterOrderListDTO;
import com.yht.demo.entity.NavigationTab;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * app首页导航选项卡 服务类
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
public interface INavigationTabService {

    Result getHomePageNavigationList(ParameterOrderListDTO parameterOrderListDTO);
}
