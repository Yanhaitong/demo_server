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
     * @param extno
     * @return
     */
    String send(String mobile, String msg);
}
