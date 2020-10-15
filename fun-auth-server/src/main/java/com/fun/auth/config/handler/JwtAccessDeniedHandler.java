package com.fun.auth.config.handler;

import com.alibaba.fastjson.JSONObject;
import com.fun.auth.result.R;
import com.fun.auth.utils.ServletUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权异常处理
 *
 * @author MrDJun 2020/10/6
 */
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        // This is invoked when user tries to access a secured REST resource without the necessary authorization
        // We should just send a 403 Forbidden response because there is no 'error' page to redirect to
        // Here you can place any message you want
        ServletUtils.renderString(response, JSONObject.toJSONString(R.forbidden()));
    }
}
