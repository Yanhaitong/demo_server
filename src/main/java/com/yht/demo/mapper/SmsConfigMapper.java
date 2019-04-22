package com.yht.demo.mapper;

import com.yht.demo.entity.SmsConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 短信配置 Mapper 接口
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
public interface SmsConfigMapper extends BaseMapper<SmsConfig> {

    String getValueByKey(String key);
}
