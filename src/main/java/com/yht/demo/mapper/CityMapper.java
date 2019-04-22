package com.yht.demo.mapper;

import com.yht.demo.entity.City;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
public interface CityMapper extends BaseMapper<City> {

    List<String> selectAllCityList();
}
