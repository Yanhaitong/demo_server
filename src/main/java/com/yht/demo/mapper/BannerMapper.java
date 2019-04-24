package com.yht.demo.mapper;

import com.yht.demo.dto.ResultBannerDTO;
import com.yht.demo.entity.Banner;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 轮播图表 Mapper 接口
 * </p>
 *
 * @author generator
 * @since 2019-04-24
 */
public interface BannerMapper extends BaseMapper<Banner> {

    List<ResultBannerDTO> selectBannerListByMap(@Param("clientId") String clientId);
}
