package com.yht.demo.mapper;

import com.yht.demo.entity.AmaldarAuth;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 经理face++认证表 Mapper 接口
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
public interface AmaldarAuthMapper extends BaseMapper<AmaldarAuth> {

    AmaldarAuth getAmaldarAuthInfoByUserId(@Param("userId") Integer UserId);
}
