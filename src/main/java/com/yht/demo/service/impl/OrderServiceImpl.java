package com.yht.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yht.demo.common.BaseServiceImpl;
import com.yht.demo.common.RedisUtils;
import com.yht.demo.common.Result;
import com.yht.demo.entity.dto.OrderDetailsReturnDTO;
import com.yht.demo.entity.dto.OrderListReceiveDTO;
import com.yht.demo.entity.dto.SearchConditionsReturnDTO;
import com.yht.demo.entity.model.User;
import com.yht.demo.mapper.OrderMapper;
import com.yht.demo.mapper.SearchConditionsMapper;
import com.yht.demo.mapper.UserMapper;
import com.yht.demo.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SearchConditionsMapper searchConditionsMapper;

    @Override
    public Result getHomePageOrderList(OrderListReceiveDTO orderListReceiveDTO) {
        Page page = new Page();
        page.setSize(orderListReceiveDTO.getPageSize());
        page.setCurrent(orderListReceiveDTO.getPageNum());
        IPage<OrderDetailsReturnDTO> orderDetailsReturnDTOIPage = orderMapper.selectOrderListByMap(page, orderListReceiveDTO);
        return Result.success(orderDetailsReturnDTOIPage);
    }

    @Override
    public Result getOrderDetailsById(String orderId) {
        OrderDetailsReturnDTO orderDetailsReturnDTO = orderMapper.getOrderDetailsById(orderId);
        return Result.success(orderDetailsReturnDTO);
    }

    @Override
    public Result getHomePageCityList(OrderListReceiveDTO orderListReceiveDTO) {
        return null;
    }

    @Override
    public Result getHomePageSearchConditions(String clientName) {
        List<SearchConditionsReturnDTO> searchConditionsReturnDTOList = searchConditionsMapper.getSearchConditionsList(clientName);
        return Result.success(searchConditionsReturnDTOList);
    }

}
