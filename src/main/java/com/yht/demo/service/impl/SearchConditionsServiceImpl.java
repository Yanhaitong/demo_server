package com.yht.demo.service.impl;

import com.yht.demo.entity.model.SearchConditions;
import com.yht.demo.mapper.SearchConditionsMapper;
import com.yht.demo.service.ISearchConditionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * app搜索条件 服务实现类
 * </p>
 *
 * @author yanht
 * @since 2019-04-20
 */
@Service
public class SearchConditionsServiceImpl extends ServiceImpl<SearchConditionsMapper, SearchConditions> implements ISearchConditionsService {

}
