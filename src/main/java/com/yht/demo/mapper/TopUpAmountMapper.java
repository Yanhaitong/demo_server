package com.yht.demo.mapper;

import com.yht.demo.entity.dto.ResultTopUpAmountDTO;
import com.yht.demo.entity.model.TopUpAmount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

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

    List<ResultTopUpAmountDTO> getTopUpAmount(@Param("clientName") String clientName);
}
