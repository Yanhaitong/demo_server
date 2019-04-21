package com.yht.demo.controller;


import com.yht.demo.common.MsgConstant;
import com.yht.demo.common.Result;
import com.yht.demo.entity.dto.ParameterBaseDTO;
import com.yht.demo.service.IPayRecordService;
import com.yht.demo.service.ITopUpAmountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.yht.demo.common.BaseController;

/**
 * <p>
 * 支付相关 前端控制器
 * </p>
 *
 * @author yanht
 * @since 2019-04-20
 */
@Api("订单支付管理")
@RestController
@RequestMapping("/pay")
public class PayController extends BaseController {

    @Autowired
    private ITopUpAmountService topUpAmountService;
    @Autowired
    private IPayRecordService payRecordService;

    @PostMapping("/topUpAmountInfo")
    @ApiOperation(value = "充值金额信息")
    public Result topUpAmountInfo(@RequestParam String clientName) {
        if (StringUtils.isEmpty(clientName)){
            return Result.error(500, MsgConstant.PARAMETER_IS_NULL);
        }
        return topUpAmountService.topUpAmountInfo(clientName);
    }

    @PostMapping("/getPayRecordList")
    @ApiOperation(value = "获取支付记录列表")
    public Result getPayRecordList(@RequestBody ParameterBaseDTO parameterBaseDTO) {
        if (StringUtils.isEmpty(parameterBaseDTO.getToken()) || StringUtils.isEmpty(parameterBaseDTO.getClientName())){
            return Result.error(500, MsgConstant.PARAMETER_IS_NULL);
        }
        return payRecordService.getPayRecordList(parameterBaseDTO);
    }
}

