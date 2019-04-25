package com.yht.demo.common.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
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
            //msg = msg.replace("{code}", code);
            HashMap<String, Object> result = null;

           /* CCPRestSDK restAPI = new CCPRestSDK();
            restAPI.init("app.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
            restAPI.setAccount("8a216da86a43ea63016a5239f89f0c70", "4ecb83ec7a3e462f92ed42ead0f932b3");// 初始化主帐号和主帐号TOKEN
            restAPI.setAppId("8a216da86a43ea63016a5239f8ef0c76");// 初始化应用ID
            result = restAPI.sendTemplateSMS(mobileNo,"1" ,new String[]{code,"5"});

            System.out.println("SDKTestSendTemplateSMS result=" + result);

            if("000000".equals(result.get("statusCode"))){
                //正常返回输出data包体信息（map）
                HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
                Set<String> keySet = data.keySet();
                for(String key:keySet){
                    Object object = data.get(key);
                    System.out.println(key +" = "+object);
                }
            }else{
                //异常返回输出错误码和错误信息
                System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
            }*/
        } catch (Exception e) {
            log.error("sendVerifyLoginSMS==============" + e.getMessage());
        }
    }
}
