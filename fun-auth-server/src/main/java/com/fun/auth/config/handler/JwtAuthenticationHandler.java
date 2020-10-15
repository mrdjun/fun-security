package com.fun.auth.config.handler;

import com.alibaba.fastjson.JSONObject;
import com.fun.auth.result.R;
import com.fun.auth.utils.ServletUtils;
import com.fun.auth.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证异常处理
 *
 * @author MrDJun 2020/10/6
 */
@Slf4j
public class JwtAuthenticationHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        // This is invoked when user tries to access a secured REST resource without supplying any credentials
        // We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
        // Here you can place any message you want
        ServletUtils.renderString(response, JSONObject.toJSONString(R.failed(MessageUtils.message("member.login.fail"))));
    }
}
