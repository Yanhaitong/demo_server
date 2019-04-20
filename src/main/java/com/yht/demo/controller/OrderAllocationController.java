package com.yht.demo.controller;


import com.yht.demo.common.BaseController;
import com.yht.demo.common.Result;
import com.yht.demo.entity.dto.AmaldarOrderListReceiveDTO;
import com.yht.demo.service.IOrderAllocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 抢单记录表 前端控制器
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
@Api("已抢订单管理")
@RestController
@RequestMapping("/orderAllocation")
public class OrderAllocationController extends BaseController {

    @Autowired
    private IOrderAllocationService orderAllocationService;

    @PostMapping("/vieForOrder")
    @ApiOperation(value = "经理抢单接口")
    public Result vieForOrder(@RequestParam String token, @RequestParam String orderId, @RequestParam String clientName) {
        if (token == null || orderId == null || clientName == null){
            return Result.error(500, "请求参数错误");
        }
        return orderAllocationService.vieForOrder(token, orderId, clientName);
    }

    @PostMapping("/amaldarOrderList")
    @ApiOperation(value = "经理已抢订单列表")
    public Result amaldarOrderList(@RequestBody AmaldarOrderListReceiveDTO amaldarOrderListReceiveDTO) {
        if (amaldarOrderListReceiveDTO == null){
            return Result.error(500, "请求错误");
        }
        return orderAllocationService.amaldarOrderList(amaldarOrderListReceiveDTO);
    }

}

