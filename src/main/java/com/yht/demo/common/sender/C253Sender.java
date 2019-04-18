package com.yht.demo.common.sender;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;

/**
 * c253
 *
 * @author w
 */
public class C253Sender implements HttpSender {
    private static final Logger log = LoggerFactory.getLogger(C253Sender.class);
    /**
     * 应用地址
     */
    private static String url = "http://smssh1.253.com/msg/";
    /**
     * 账号
     */
    private static String account = "N6134036";
    /**
     * 密码
     */
    private static String pswd = "yIRs8az2nv3d38";
    /**
     * 是否需要状态报告，需要true，不需要false
     */
    private static boolean needstatus = true;

    @Override
    public String send(String mobile, String msg) {
        log.info("send:" + mobile + "======" + msg);
        InputStream in = null;
        ByteArrayOutputStream baos = null;
        HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
        GetMethod method = new GetMethod();
        try {
            URI base = new URI(url, false);
            method.setURI(new URI(base, "HttpBatchSendSM", false));
            method.setQueryString(new NameValuePair[]{
                    new NameValuePair("account", account),
                    new NameValuePair("pswd", pswd),
                    new NameValuePair("mobile", mobile),
                    new NameValuePair("needstatus", String.valueOf(needstatus)),
                    new NameValuePair("msg", msg),
            });
            int result = client.executeMethod(method);
            if (result == HttpStatus.SC_OK) {
                in = method.getResponseBodyAsStream();
                baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = in.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                String resultString = URLDecoder.decode(baos.toString(), "UTF-8");
                log.info(resultString);
                return resultString;
            } else {
                log.info(("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText()));
                throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return "";
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                    e.printStackTrace();
                }
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                    e.printStackTrace();
                }
            }
            method.releaseConnection();
        }
    }

}