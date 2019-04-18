package com.yht.demo.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RedisUtil {
    private static final Logger log = LoggerFactory.getLogger(RedisUtil.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //通过该标签以及该方法实现给static属性注入  
    public static RedisUtil redisUtil;

    @PostConstruct
    public void init() {
        redisUtil = this;
        redisUtil.stringRedisTemplate = this.stringRedisTemplate;
    }

    /**
     * 保存token
     *
     * @param mobileNo
     * @param token
     */
    public static void saveToken(String mobileNo, String token) {
        log.info("调用saveToken:mobileNo--" + mobileNo + ",token--" + token);
        try {
            redisUtil.stringRedisTemplate.opsForValue().set(mobileNo, token);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 删除token
     *
     * @param token
     */
    public static void deleteToken(String token) {
        log.info("调用deleteToken:token--" + token);
        try {
            redisUtil.stringRedisTemplate.delete(token);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }


}
