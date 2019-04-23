package com.yht.demo.controller;


import com.yht.demo.common.MsgConstant;
import com.yht.demo.common.Result;
import com.yht.demo.dto.ParameterBaseDTO;
import com.yht.demo.dto.ParameterPayRecordDTO;
import com.yht.demo.service.IPayRecordService;
import com.yht.demo.service.ITopUpAmountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 充值记录表 前端控制器
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */

@Api("支付管理")
@RestController
@RequestMapping("/payRecord")
public class PayRecordController {

    @Autowired
    private ITopUpAmountService topUpAmountService;
    @Autowired
    private IPayRecordService payRecordService;

    @PostMapping("/topUpAmountInfo")
    @ApiOperation(value = "充值金额信息")
    public Result topUpAmountInfo(@RequestBody ParameterBaseDTO parameterBaseDTO) {
        return topUpAmountService.topUpAmountInfo(parameterBaseDTO);
    }

    @PostMapping("/getPayRecordList")
    @ApiOperation(value = "获取支付记录列表")
    public Result getPayRecordList(@RequestBody ParameterPayRecordDTO parameterPayRecordDTO) {
        if (StringUtils.isEmpty(parameterPayRecordDTO.getToken())){
            return Result.error(500, MsgConstant.PARAMETER_IS_NULL);
        }
        return payRecordService.getPayRecordList(parameterPayRecordDTO);
    }
}

