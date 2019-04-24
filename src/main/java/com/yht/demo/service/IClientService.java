package com.yht.demo.service;

import com.yht.demo.entity.Client;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户端表 服务类
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
public interface IClientService {

    List<Client> selectClientByMap(Map<String, Object> parameterMap);
}
