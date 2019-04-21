package com.yht.demo.service.impl;

import com.yht.demo.entity.model.City;
import com.yht.demo.mapper.CityMapper;
import com.yht.demo.service.ICityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yanht
 * @since 2019-04-21
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements ICityService {

}
