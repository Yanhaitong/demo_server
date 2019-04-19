package com.yht.demo.mapper;

import com.yht.demo.entity.model.SystemConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 短信配置 Mapper 接口
 * </p>
 *
 * @author yanht
 * @since 2019-04-19
 */
public interface SystemConfigMapper extends BaseMapper<SystemConfig> {

    String getValueByKey(String key);
}
