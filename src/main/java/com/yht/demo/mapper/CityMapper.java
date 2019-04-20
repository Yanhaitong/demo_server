package com.yht.demo.mapper;

import com.yht.demo.entity.model.City;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author yanht
 * @since 2019-04-20
 */
public interface CityMapper extends BaseMapper<City> {

    List<String> selectAllCityList();
}
