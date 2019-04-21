package com.yht.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yht.demo.common.BaseServiceImpl;
import com.yht.demo.common.Result;
import com.yht.demo.entity.dto.ResultOrderDetailsDTO;
import com.yht.demo.entity.dto.ParameterOrderListDTO;
import com.yht.demo.entity.dto.ResultSearchConditionsDTO;
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
    private SearchConditionsMapper searchConditionsMapper;

    @Override
    public Result getHomePageOrderList(ParameterOrderListDTO parameterOrderListDTO) {
        Page page = new Page();
        page.setSize(parameterOrderListDTO.getPageSize());
        page.setCurrent(parameterOrderListDTO.getPageNum());
        IPage<ResultOrderDetailsDTO> resultOrderDetailsDTOIPage = orderMapper.selectOrderListByMap(page, parameterOrderListDTO);
        return Result.success(resultOrderDetailsDTOIPage);
    }

    @Override
    public Result getOrderDetailsById(String orderId) {
        ResultOrderDetailsDTO resultOrderDetailsDTO = orderMapper.getOrderDetailsById(orderId);
        return Result.success(resultOrderDetailsDTO);
    }

}
