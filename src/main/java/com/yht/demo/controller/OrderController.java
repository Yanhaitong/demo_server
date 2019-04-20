package com.yht.demo.controller;


import com.yht.demo.common.BaseController;
import com.yht.demo.common.Result;
import com.yht.demo.entity.dto.OrderListReceiveDTO;
import com.yht.demo.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Result getHomePageOrderList(@RequestBody OrderListReceiveDTO orderListReceiveDTO) {
        if (orderListReceiveDTO == null){
            return Result.error(500, "请求错误");
        }
        return orderService.getHomePageOrderList(orderListReceiveDTO);
    }

    @PostMapping("/getOrderDetailsById")
    @ApiOperation(value = "获取订单详情")
    public Result getOrderDetailsById(@RequestParam String orderId) {
        if (orderId == null){
            return Result.error(500, "请求错误");
        }
        return orderService.getOrderDetailsById(orderId);
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
    public Result getHomePageSearchConditions(@RequestParam String clientName) {
        if (clientName == null){
            return Result.error(500, "请求错误");
        }
        return orderService.getHomePageSearchConditions(clientName);
    }

}

