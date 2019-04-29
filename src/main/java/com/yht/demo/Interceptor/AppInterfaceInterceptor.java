package com.yht.demo.Interceptor;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yht.demo.common.MsgConstant;
import com.yht.demo.common.RedisUtils;
import com.yht.demo.common.Result;
import com.yht.demo.dto.ParameterBaseDTO;
import com.yht.demo.entity.Client;
import com.yht.demo.service.IClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppInterfaceInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AppInterfaceInterceptor.class);

    @Autowired
    private IClientService clientService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /**
         * 对来自后台的请求统一进行日志处理
         */
        /*RepeatedlyReadRequestWrapper requestWrapper;
        if (request instanceof RepeatedlyReadRequestWrapper) {
            // 签名处理过程 start....
            requestWrapper = (RepeatedlyReadRequestWrapper) request;
            logger.info("请求Body: {} ", getBodyString(requestWrapper));
            // 签名处理过程 end....
        }
        // 默认记录后台接口请求日志记录
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        logger.info(String.format("请求参数, url: %s, method: %s, uri: %s, params: %s ", url, method, uri, queryString));*/

        AppInterfaceWrapper appInterfaceWrapper;
        if (request instanceof AppInterfaceWrapper) {
            appInterfaceWrapper = (AppInterfaceWrapper) request;
            logger.info("请求Body: {} ", getBodyString(appInterfaceWrapper));

            JSONObject jsonObject = JSONObject.parseObject(getBodyString(appInterfaceWrapper));
            ParameterBaseDTO parameterBaseDTO = JSON.toJavaObject(jsonObject, ParameterBaseDTO.class);

            //过滤基础必要字段
            if (StringUtils.isEmpty(parameterBaseDTO.getToken())) {
                this.sendJsonMessage(response, Result.error(500, MsgConstant.TOKEN_IS_NULL));
                return false;
            }
            if (StringUtils.isEmpty(parameterBaseDTO.getClientName())) {
                this.sendJsonMessage(response, Result.error(500, MsgConstant.CLIENT_NAME_IS_NULL));
                return false;
            }
            if (StringUtils.isEmpty(parameterBaseDTO.getClientId())) {
                this.sendJsonMessage(response, Result.error(500, MsgConstant.CLIENT_ID_IS_NULL));
                return false;
            }
            if (StringUtils.isEmpty(parameterBaseDTO.getClientType())) {
                this.sendJsonMessage(response, Result.error(500, MsgConstant.CLIENT_TYPE_IS_NULL));
                return false;
            }

            //判断clientId和clientName是否一一对应
            Map<String, Object> parameterMap = new HashMap<>();
            parameterMap.put("id", parameterBaseDTO.getClientId());
            parameterMap.put("name", parameterBaseDTO.getClientName());
            List<Client> clientList = clientService.selectClientByMap(parameterMap);
            if (clientList.size() == 0) {
                this.sendJsonMessage(response, Result.error(500, MsgConstant.CLIENT_EXCEPTION));
                return false;
            }

            //判断用户是否存在
            String userId = RedisUtils.getUserIdByToken(parameterBaseDTO.getToken());
            if (StringUtils.isEmpty(userId)) {
                this.sendJsonMessage(response, Result.error(500, MsgConstant.USER_ID_IS_NULL));
                return false;
            }

        }
        return true;
    }

    /**
     * 获取请求Body
     *
     * @param request
     * @return
     */
    public static String getBodyString(final ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = cloneInputStream(request.getInputStream());
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    /**
     * Description: 复制输入流</br>
     *
     * @param inputStream
     * @return</br>
     */
    public static InputStream cloneInputStream(ServletInputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buffer)) > -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            byteArrayOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return byteArrayInputStream;
    }

    /**
     * 将某个对象转换成json格式并发送到客户端
     *
     * @param response
     * @param obj
     * @throws Exception
     */
    public static void sendJsonMessage(HttpServletResponse response, Object obj) throws Exception {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat));
        writer.close();
        response.flushBuffer();
    }

}