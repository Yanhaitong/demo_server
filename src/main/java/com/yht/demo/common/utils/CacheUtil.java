package com.yht.demo.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheUtil {

    private static final Logger log= LoggerFactory.getLogger(CacheUtil.class);

    /**
     * 存放图片验证码
     * @param key
     */
    public static void saveCaptcha(String key) {


        /*log.info("调用saveCaptcha:key--"+key);
        RedisClient redisClient = null;
        try {
            redisClient = new RedisClient();
            redisClient.delete(key);
            redisClient.expire(key, "0", 60*30);
        } catch (Exception e) {
            log.error(e.getMessage());
        }finally{
            if(redisClient!=null){
                redisClient.returnResource();
            }
        }*/
    }
}
