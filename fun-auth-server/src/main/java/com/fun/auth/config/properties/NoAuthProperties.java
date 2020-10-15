package com.fun.auth.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 白名单
 * 需要权限才能访问的 URLs
 *
 * @author MrDJun 2020/9/29
 */
@Setter
@Getter
@Component
public class NoAuthProperties {
    private static final String[] ENDPOINTS = {
            "/oauth/**",
            "/actuator/**",
            "/*/v2/api-docs",
            "/swagger/api-docs",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/webjars/**",
    };

    private static final String[] staticResources = {
            "/",
            "/*.html",
            "/favicon.ico",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js",
    };

    private String[] httpUrls = {
//        "/product/**"
    };

    /**
     * 设置认证后不需要判断具体权限的 url，所有登录的人都能访问
     */
    private String[] menusPaths = {};

    public String[] getUrls() {
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(ENDPOINTS));
        list.addAll(Arrays.asList(httpUrls));
        list.addAll(Arrays.asList(staticResources));

        String[] urls = new String[list.size()];
        return list.toArray(urls);
    }
}
