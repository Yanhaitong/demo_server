package com.yht.demo.mapper;

import com.yht.demo.entity.dto.ResultSearchConditionsDTO;
import com.yht.demo.entity.model.SearchConditions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * app搜索条件 Mapper 接口
 * </p>
 *
 * @author yanht
 * @since 2019-04-20
 */
public interface SearchConditionsMapper extends BaseMapper<SearchConditions> {

    List<ResultSearchConditionsDTO> getSearchConditionsList(@Param("clientName") String clientName);
}
