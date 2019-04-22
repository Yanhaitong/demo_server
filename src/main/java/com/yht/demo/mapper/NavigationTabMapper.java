package com.yht.demo.mapper;

import com.yht.demo.dto.ResultNavigationTabDTO;
import com.yht.demo.entity.NavigationTab;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * app首页导航选项卡 Mapper 接口
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
public interface NavigationTabMapper extends BaseMapper<NavigationTab> {

    List<ResultNavigationTabDTO> getNavigationTabList(@Param("clientName") String clientName);
}
