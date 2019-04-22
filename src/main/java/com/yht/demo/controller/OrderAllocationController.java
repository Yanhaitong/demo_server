package com.yht.demo.controller;


import com.yht.demo.common.Result;
import com.yht.demo.dto.ParameterAmaldarOrderListDTO;
import com.yht.demo.dto.ParameterVieForOrderDTO;
import com.yht.demo.dto.ResultOrderDetailsDTO;
import com.yht.demo.service.IOrderAllocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 抢单记录表 前端控制器
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
@Api("已抢订单管理")
@RestController
@RequestMapping("/orderAllocation")
public class OrderAllocationController {

    @Autowired
    private IOrderAllocationService orderAllocationService;

    @PostMapping("/vieForOrder")
    @ApiOperation(value = "经理抢单接口")
    public Result vieForOrder(@RequestBody ParameterVieForOrderDTO parameterVieForOrderDTO) {
        if (StringUtils.isEmpty(parameterVieForOrderDTO.getToken()) || StringUtils.isEmpty(parameterVieForOrderDTO.getOrderId()) ||
                StringUtils.isEmpty(parameterVieForOrderDTO.getClientId()) || StringUtils.isEmpty(parameterVieForOrderDTO.getClientType())){
            return Result.error(500, "请求参数错误");
        }
        return orderAllocationService.vieForOrder(parameterVieForOrderDTO);
    }

    @PostMapping("/amaldarOrderList")
    @ApiOperation(value = "经理已抢订单列表")
    public Result<ResultOrderDetailsDTO> amaldarOrderList(@RequestBody ParameterAmaldarOrderListDTO parameterAmaldarOrderListDTO) {
        if (StringUtils.isEmpty(parameterAmaldarOrderListDTO.getToken()) || StringUtils.isEmpty(parameterAmaldarOrderListDTO.getPageNum()) ||
                StringUtils.isEmpty(parameterAmaldarOrderListDTO.getPageSize()) || StringUtils.isEmpty(parameterAmaldarOrderListDTO.getClientId()) ||
                StringUtils.isEmpty(parameterAmaldarOrderListDTO.getClientType()) ){
            return Result.error(500, "请求错误");
        }
        return orderAllocationService.amaldarOrderList(parameterAmaldarOrderListDTO);
    }
}

