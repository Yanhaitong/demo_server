package com.yht.demo.service.impl;

import com.yht.demo.common.BaseServiceImpl;
import com.yht.demo.entity.Client;
import com.yht.demo.mapper.ClientMapper;
import com.yht.demo.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户端表 服务实现类
 * </p>
 *
 * @author generator
 * @since 2019-04-22
 */
@Service
public class ClientServiceImpl extends BaseServiceImpl implements IClientService {

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public List<Client> selectClientByMap(Map<String, Object> parameterMap) {
        return clientMapper.selectByMap(parameterMap);
    }
}
