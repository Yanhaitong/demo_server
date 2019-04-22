package com.yht.demo.mapper;

import com.yht.demo.entity.Client;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 客户端表 Mapper 接口
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
public interface ClientMapper extends BaseMapper<Client> {

    Client selectClientByName(@Param("clientName") String clientName);
}
