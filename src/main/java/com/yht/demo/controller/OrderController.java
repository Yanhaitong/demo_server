package com.yht.demo.controller;


import com.yht.demo.common.MsgConstant;
import com.yht.demo.common.Result;
import com.yht.demo.dto.ParameterOrderDetailsDTO;
import com.yht.demo.dto.ParameterOrderListDTO;
import com.yht.demo.dto.ResultOrderDetailsDTO;
import com.yht.demo.service.IOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping("/getHomePageOrderList")
    @ApiOperation(value = "获取首页订单列表")
    public Result<ResultOrderDetailsDTO> getHomePageOrderList(@RequestBody ParameterOrderListDTO parameterOrderListDTO) {
        if (parameterOrderListDTO == null){
            return Result.error(500, MsgConstant.PARAMETER_IS_NULL);
        }
        return orderService.getHomePageOrderList(parameterOrderListDTO);
    }

    @PostMapping("/getOrderDetailsById")
    @ApiOperation(value = "获取订单详情")
    public Result<ResultOrderDetailsDTO> getOrderDetailsById(@RequestBody ParameterOrderDetailsDTO parameterOrderDetailsDTO) {
        if (StringUtils.isEmpty(parameterOrderDetailsDTO.getOrderId()) || StringUtils.isEmpty(parameterOrderDetailsDTO.getToken())){
            return Result.error(500, MsgConstant.PARAMETER_IS_NULL);
        }
        return orderService.getOrderDetailsById(parameterOrderDetailsDTO);
    }
}

