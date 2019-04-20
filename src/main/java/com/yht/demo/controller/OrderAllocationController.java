package com.yht.demo.controller;


import com.yht.demo.common.Result;
import com.yht.demo.entity.dto.OrderListReceiveDTO;
import com.yht.demo.service.IOrderAllocationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.yht.demo.common.BaseController;

/**
 * <p>
 * 抢单记录表 前端控制器
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
@RestController
@RequestMapping("/orderAllocation")
public class OrderAllocationController extends BaseController {

    @Autowired
    private IOrderAllocationService orderAllocationService;

    @PostMapping("/vieForOrder")
    @ApiOperation(value = "经理抢单接口")
    public Result vieForOrder(String token, String orderId, String clientName) {
        if (token == null || orderId == null || clientName == null){
            return Result.error(500, "请求参数错误");
        }
        return orderAllocationService.vieForOrder(token, orderId, clientName);
    }
}

