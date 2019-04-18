package com.yht.demo.common.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class SMSUtils {
    private static final Logger log = LoggerFactory.getLogger(SMSUtils.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //通过该标签以及该方法实现给static属性注入  
    public static SMSUtils sMSUtils;
    @PostConstruct
    public void init() {
        sMSUtils = this;
        sMSUtils.stringRedisTemplate = this.stringRedisTemplate;
    }

    private static HttpSender sender = new C253Sender();//253

    /**
     * 验证码生成器
     */
    public static String getCode(int length) {
        if (length < 1) {
            return null;
        }
        String code = "";
        for (int i = 0; i < length; i++) {
            char c = (char) (randomInt(0, 9) + '0');
            code += String.valueOf(c);
        }
        return code;
    }

    /**
     * 生成随机数
     */
    public static int randomInt(int from, int to) {
        Random r = new Random();
        return from + r.nextInt(to - from);
    }

    /**
     * 发送验证码短信 参数：手机号码
     */
    public static void sendVerifyLoginSMS(String mobileNo, String msg) {
        log.info("sendVerifyLoginSMS:mobileNo--" + mobileNo);
        try {
            String code = SMSUtils.getCode(6);
            sMSUtils.stringRedisTemplate.delete("SMS" + mobileNo);
            sMSUtils.stringRedisTemplate.opsForValue().set("SMS" + mobileNo, code, 30, TimeUnit.MINUTES);
            msg = msg.replace("{code}", code);
            String returnString = sender.send(mobileNo, msg);
            log.info(returnString);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
        }
    }
}
