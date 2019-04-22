package com.yht.demo.mapper;

import com.yht.demo.dto.ResultSearchConditionsDTO;
import com.yht.demo.entity.SearchConditions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * app搜索条件 Mapper 接口
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
public interface SearchConditionsMapper extends BaseMapper<SearchConditions> {

    List<ResultSearchConditionsDTO> getSearchConditionsList(@Param("clientId") String clientId);
}
