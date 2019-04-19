package com.yht.demo.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.xml.ws.Action;
import java.util.concurrent.TimeUnit;

public class CacheUtil {

    private static final Logger log = LoggerFactory.getLogger(CacheUtil.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 存放图片验证码
     *
     * @param key
     */
    public void saveCaptcha(String key) {
        log.info("调用saveCaptcha==========" + key);
        stringRedisTemplate.delete(key);
        stringRedisTemplate.expire(key, 60 * 30, TimeUnit.SECONDS);
    }
}
