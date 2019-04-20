package com.yht.demo.controller;


import com.yht.demo.common.Result;
import com.yht.demo.entity.dto.OrderListReceiveDTO;
import com.yht.demo.mapper.TopUpAmountMapper;
import com.yht.demo.service.IPayRecordService;
import com.yht.demo.service.ITopUpAmountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.yht.demo.common.BaseController;

/**
 * <p>
 * 支付相关 前端控制器
 * </p>
 *
 * @author yanht
 * @since 2019-04-20
 */
@RestController
@RequestMapping("/pay")
public class PayController extends BaseController {

    @Autowired
    private ITopUpAmountService topUpAmountService;
    @Autowired
    private IPayRecordService payRecordService;

    @PostMapping("/topUpInfo")
    @ApiOperation(value = "充值信息")
    public Result topUpInfo(String clientName) {
        if (clientName == null){
            return Result.error(500, "请求错误");
        }
        return topUpAmountService.topUpInfo(clientName);
    }

    @PostMapping("/getPayRecordList")
    @ApiOperation(value = "获取支付记录列表")
    public Result getPayRecordList(String token, String clientName) {
        if (clientName == null || token == null){
            return Result.error(500, "请求错误");
        }
        return payRecordService.getPayRecordList(token, clientName);
    }
}

