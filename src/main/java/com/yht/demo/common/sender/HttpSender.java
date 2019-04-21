package com.yht.demo.common.sender;

/**
 * httpsender
 *
 * @author w
 */
public interface HttpSender {

    /**
     * send message
     *
     * @param mobile
     * @param msg
     * @return
     */
    String send(String mobile, String msg);
}
