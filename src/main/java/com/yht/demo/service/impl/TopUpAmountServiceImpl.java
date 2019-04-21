package com.yht.demo.service.impl;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.yht.demo.common.Result;
import com.yht.demo.entity.dto.ResultTopUpAmountDTO;
import com.yht.demo.entity.model.TopUpAmount;
import com.yht.demo.mapper.SystemConfigMapper;
import com.yht.demo.mapper.TopUpAmountMapper;
import com.yht.demo.service.ITopUpAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 充值金额表 服务实现类
 * </p>
 *
 * @author yanht
 * @since 2019-04-20
 */
@Service
public class TopUpAmountServiceImpl extends Serializers.Base implements ITopUpAmountService {

    @Autowired
    private TopUpAmountMapper topUpAmountMapper;

    @Autowired
    private SystemConfigMapper systemConfigMapper;

    @Override
    public Result topUpAmountInfo(String clientName) {
        Map<String, Object> parameterMap = new HashMap<>();

        List<ResultTopUpAmountDTO> topUpAmountDTOList = topUpAmountMapper.getTopUpAmount(clientName);
        parameterMap.put("topUpAmountList", topUpAmountDTOList);

        String alipay = systemConfigMapper.getValueByKey("alipay");
        parameterMap.put("alipay", alipay);

        String wxpay = systemConfigMapper.getValueByKey("wxpay");
        parameterMap.put("wxpay", wxpay);

        return Result.success(parameterMap);
    }

}