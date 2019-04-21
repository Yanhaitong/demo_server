package com.yht.demo.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    private static final Logger log = LoggerFactory.getLogger(RedisUtils.class);
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
    public static void saveToken(String token, String mobileNo) {
        log.info("调用saveToken:mobileNo--" + mobileNo + ",token--" + token);
        try {
            redisUtils.stringRedisTemplate.opsForValue().set(token, mobileNo);
            log.info("token======================" + redisUtils.stringRedisTemplate.opsForValue().get(token));
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
     *
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

    /**
     * 记录用户每日抢单数量
     *
     * @param key
     * @return
     */
    public static void setVieForOrderCount(Integer key) {
        log.info("setVieForOrderCount:key--" + key);
        try {
            Long vieForOrderCount = redisUtils.stringRedisTemplate.opsForList().size("VIEFORORDERCOUNT" + key);
            if (vieForOrderCount == null || vieForOrderCount == 0) {
                final GregorianCalendar calendar = new GregorianCalendar();
                calendar.add(GregorianCalendar.DATE, 1);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                long timeout = calendar.getTime().getTime() / 1000;
                redisUtils.stringRedisTemplate.opsForList().leftPush(key + "", "0");
                redisUtils.stringRedisTemplate.expire(key + "", timeout, TimeUnit.SECONDS);
            } else {
                redisUtils.stringRedisTemplate.opsForList().leftPush(key + "", "0");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 获取用户每日抢单数量
     *
     * @param key
     * @return
     */
    public static long getVieForOrderCount(Integer key) {
        log.info("vieForOrderCounr:key--" + key);
        try {
            Long vieForOrderCount = redisUtils.stringRedisTemplate.opsForList().size("VIEFORORDERCOUNT" + key);
            if (vieForOrderCount == null) {
                return 0;
            }
            return vieForOrderCount;
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0;
        }
    }
}
