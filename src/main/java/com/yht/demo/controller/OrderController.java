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
    @ApiOperation(value = "获取首页订单列表")
    public Result getHomePageSearchConditions(@RequestBody OrderListReceiveDTO orderListReceiveDTO) {
        if (orderListReceiveDTO == null){
            return Result.error(500, "请求错误");
        }
        return orderService.getHomePageSearchConditions(orderListReceiveDTO);
    }

    @PostMapping("/getAllOrderInfo")
    @ApiOperation(value = "获取全部订单信息")
    public Result getAllOrderInfo(@RequestBody OrderListReceiveDTO orderListReceiveDTO) {
        if (orderListReceiveDTO == null){
            return Result.error(500, "请求错误");
        }
        return orderService.getAllOrderInfo(orderListReceiveDTO);
    }

    @PostMapping("/getOrderInfoById")
    @ApiOperation(value = "获取订单信息")
    public Result getOrderInfoById(@RequestBody OrderListReceiveDTO orderListReceiveDTO) {
        if (orderListReceiveDTO == null){
            return Result.error(500, "请求错误");
        }
        return orderService.getAllOrderInfo(orderListReceiveDTO);
    }

    @PostMapping("/myOrderList")
    @ApiOperation(value = "经理已抢订单列表")
    public Result myOrderList(@RequestBody OrderListReceiveDTO orderListReceiveDTO) {
        if (orderListReceiveDTO == null){
            return Result.error(500, "请求错误");
        }
        return orderService.myOrderList(orderListReceiveDTO);
    }


}

