package com.yht.demo.controller;


import com.yht.demo.common.BaseController;
import com.yht.demo.common.MsgConstant;
import com.yht.demo.common.Result;
import com.yht.demo.entity.dto.*;
import com.yht.demo.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
@Api("订单管理")
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

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

