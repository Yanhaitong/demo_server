package com.yht.demo.service.impl;

import com.yht.demo.common.BaseServiceImpl;
import com.yht.demo.common.Result;
import com.yht.demo.dto.ParameterOrderListDTO;
import com.yht.demo.entity.NavigationTab;
import com.yht.demo.mapper.NavigationTabMapper;
import com.yht.demo.service.INavigationTabService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * app首页导航选项卡 服务实现类
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
@Service
public class NavigationTabServiceImpl extends BaseServiceImpl implements INavigationTabService {

    @Override
    public Result getHomePageNavigationList(ParameterOrderListDTO orderListReceiveDTO) {
        return null;
    }
}
