package com.yht.demo.service.impl;

import com.yht.demo.common.BaseServiceImpl;
import com.yht.demo.common.Result;
import com.yht.demo.entity.dto.OrderListReceiveDTO;
import com.yht.demo.service.INavigationTabService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * app首页导航选项卡 服务实现类
 * </p>
 *
 * @author yanht
 * @since 2019-04-20
 */
@Service
public class NavigationTabServiceImpl extends BaseServiceImpl implements INavigationTabService {

    @Override
    public Result getHomePageNavigationList(OrderListReceiveDTO orderListReceiveDTO) {
        return null;
    }
}
