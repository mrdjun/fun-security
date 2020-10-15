package com.fun.auth.constant;


/**
 * 通用常量信息
 *
 * @author DJun
 */
public interface SysConstant {
    // ========================== Status Constant ================================
    /** 成功 */
    String SUCCESS = "1";
    /** 失败 */
    String FAIL = "0";
    /** 正常 */
    String NORMAL = "1";
    /** 禁用 */
    String FORBIDDEN = "0";
    /** 已删除 */
    String IS_DELETE = "1";
    /** 未删除 */
    String IS_NOT_DELETE = "0";

    // ========================== Security Constant ================================
    /**  请求头中的 token 属性名 */
    String HEADER_TOKEN = "Authorization";
    /** Token 前缀 */
    String TOKEN_PREFIX = "Bearer ";
    /** 登录授权的 URI */
    String LOGIN_URI = "/login";
}
