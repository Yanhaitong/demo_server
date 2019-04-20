package com.yht.demo.mapper;

import com.yht.demo.entity.dto.NavigationTabReturnDTO;
import com.yht.demo.entity.model.NavigationTab;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * app首页导航选项卡 Mapper 接口
 * </p>
 *
 * @author yanht
 * @since 2019-04-20
 */
public interface NavigationTabMapper extends BaseMapper<NavigationTab> {

    List<NavigationTabReturnDTO> getNavigationTabList(@Param("clientName") String clientName);
}
