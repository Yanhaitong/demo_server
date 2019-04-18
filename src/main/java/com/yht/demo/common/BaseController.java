package com.yht.demo.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

public class BaseController {
    public Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public StringRedisTemplate stringRedisTemplate;
}
