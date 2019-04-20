package com.yht.demo.controller;


import com.yht.demo.common.Result;
import com.yht.demo.entity.dto.OrderListReceiveDTO;
import com.yht.demo.service.IOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.yht.demo.common.BaseController;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    private IOrderService orderService;

    @PostMapping("/getHomePageOrderList")
    @ApiOperation(value = "获取首页订单列表")
    public Result getHomePageOrderList(@RequestBody OrderListReceiveDTO orderListReceiveDTO) {
        if (orderListReceiveDTO == null){
            return Result.error(500, "请求错误");
        }
        return orderService.getHomePageOrderList(orderListReceiveDTO);
    }

    @PostMapping("/getHomePageCityList")
    @ApiOperation(value = "获取首页城市列表")
    public Result getHomePageCityList(@RequestBody OrderListReceiveDTO orderListReceiveDTO) {
        if (orderListReceiveDTO == null){
            return Result.error(500, "请求错误");
        }
        return orderService.getHomePageCityList(orderListReceiveDTO);
    }

    @PostMapping("/getHomePageSearchConditions")
    @ApiOperation(value = "获取首页搜索条件")
    public Result getHomePageSearchConditions(@RequestBody OrderListReceiveDTO orderListReceiveDTO) {
        if (orderListReceiveDTO == null){
            return Result.error(500, "请求错误");
        }
        return orderService.getHomePageSearchConditions(orderListReceiveDTO);
    }

    @PostMapping("/getOrderDetailsById")
    @ApiOperation(value = "获取订单详情")
    public Result getOrderDetailsById(String orderId) {
        if (orderId == null){
            return Result.error(500, "请求错误");
        }
        return orderService.getOrderDetailsById(orderId);
    }

    @PostMapping("/amaldarOrderList")
    @ApiOperation(value = "经理已抢订单列表")
    public Result amaldarOrderList(String token, String clientName) {
        if (token == null){
            return Result.error(500, "请求错误");
        }
        return orderService.amaldarOrderList(token, clientName);
    }


}

