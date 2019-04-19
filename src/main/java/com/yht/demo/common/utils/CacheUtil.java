package com.yht.demo.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

public class CacheUtil {

    private static final Logger log = LoggerFactory.getLogger(CacheUtil.class);

    /**
     * 存放图片验证码
     *
     * @param key
     */
    public static void saveCaptcha(String key) {
        log.info("调用saveCaptcha==========" + key);
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.delete(key);
        stringRedisTemplate.expire(key, 60 * 30, TimeUnit.SECONDS);
    }
}
