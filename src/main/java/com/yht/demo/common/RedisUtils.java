package com.yht.demo.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.PostConstruct;

public class RedisUtils {

    private static final Logger log= LoggerFactory.getLogger(RedisUtils.class);
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //通过该标签以及该方法实现给static属性注入  
    public static RedisUtils redisUtils;

    @PostConstruct
    public void init() {
        redisUtils = this;
        redisUtils.stringRedisTemplate = this.stringRedisTemplate;
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
            redisUtils.stringRedisTemplate.opsForValue().set(mobileNo, token);
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
            redisUtils.stringRedisTemplate.delete(token);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 通过token获取手机号
     * @param token
     * @return
     */
    public static String getMobileByToken(String token) {
        log.info("getMobileByToken:token--" + token);
        try {
            return redisUtils.stringRedisTemplate.opsForValue().get(token);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
