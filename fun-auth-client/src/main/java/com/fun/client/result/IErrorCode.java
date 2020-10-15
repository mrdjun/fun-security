package com.fun.client.result;

/**
 *
 * @author MrDJun
 * @date 2019/07/19 10:58
 */
public interface IErrorCode {
    /**
     * 获取 code
     *
     * @return code
     */
    int getCode();

    /**
     * 获取 message
     *
     * @return message
     */
    String getMessage();
}
