package com.yht.demo.mapper;

import com.yht.demo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * APP用户表 Mapper 接口
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
public interface UserMapper extends BaseMapper<User> {

    User getUserInfo(@Param("mobileNo") String mobileNo, @Param("clientId") String clientId);
}
