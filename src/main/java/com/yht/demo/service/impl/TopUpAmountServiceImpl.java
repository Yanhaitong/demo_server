package com.yht.demo.service.impl;

import com.yht.demo.common.BaseServiceImpl;
import com.yht.demo.common.Result;
import com.yht.demo.dto.ResultTopUpAmountDTO;
import com.yht.demo.entity.TopUpAmount;
import com.yht.demo.mapper.SmsConfigMapper;
import com.yht.demo.mapper.TopUpAmountMapper;
import com.yht.demo.service.ITopUpAmountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * @author generator
 * @since 2019-04-22
 */
@Service
public class TopUpAmountServiceImpl extends BaseServiceImpl implements ITopUpAmountService {

    @Autowired
    private TopUpAmountMapper topUpAmountMapper;

    @Autowired
    private SmsConfigMapper smsConfigMapper;

    @Override
    public Result topUpAmountInfo(String clientId) {
        Map<String, Object> parameterMap = new HashMap<>();

        List<ResultTopUpAmountDTO> topUpAmountDTOList = topUpAmountMapper.getTopUpAmount(clientId);
        parameterMap.put("topUpAmountList", topUpAmountDTOList);

        String alipay = smsConfigMapper.getValueByKey("alipay");
        parameterMap.put("alipay", alipay);

        String wxpay = smsConfigMapper.getValueByKey("wxpay");
        parameterMap.put("wxpay", wxpay);

        return Result.success(parameterMap);
    }
}
