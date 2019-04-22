package com.yht.demo.mapper;

import com.yht.demo.dto.ResultTopUpAmountDTO;
import com.yht.demo.entity.TopUpAmount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 充值金额表 Mapper 接口
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
public interface TopUpAmountMapper extends BaseMapper<TopUpAmount> {

    List<ResultTopUpAmountDTO> getTopUpAmount(@Param("clientId") String clientId);
}
