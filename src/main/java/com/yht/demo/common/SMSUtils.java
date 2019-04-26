package com.yht.demo.common;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
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
            //msg = msg.replace("{code}", code);
            DefaultProfile profile = DefaultProfile.getProfile("default", "<accessKeyId>", "<accessSecret>");
            IAcsClient client = new DefaultAcsClient(profile);

            CommonRequest request = new CommonRequest();
            //request.setProtocol(ProtocolType.HTTPS);
            request.setMethod(MethodType.POST);
            request.setDomain("dysmsapi.aliyuncs.com");
            request.setVersion("2017-05-25");
            request.setAction("SendSms");
            request.putQueryParameter("PhoneNumbers", mobileNo);
            request.putQueryParameter("SignName", "1");
            request.putQueryParameter("TemplateCode", "1");
            request.putQueryParameter("TemplateParam", "12");
            try {
                CommonResponse response = client.getCommonResponse(request);
                System.out.println(response.getData());
            } catch (ServerException e) {
                e.printStackTrace();
            } catch (ClientException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            log.error("sendVerifyLoginSMS==============" + e.getMessage());
        }
    }
}
