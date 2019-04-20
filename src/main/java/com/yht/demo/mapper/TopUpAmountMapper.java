package com.yht.demo.mapper;

import com.yht.demo.entity.TopUpAmount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 充值金额表 Mapper 接口
 * </p>
 *
 * @author yanht
 * @since 2019-04-20
 */
public interface TopUpAmountMapper extends BaseMapper<TopUpAmount> {

    List<TopUpAmount> getTopUpAmount(String clientName);
}
