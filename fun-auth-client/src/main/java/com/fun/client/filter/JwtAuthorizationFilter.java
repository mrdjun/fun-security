package com.fun.client.filter;

import com.alibaba.fastjson.JSONObject;
import com.fun.client.config.PublicKeyProperties;
import com.fun.client.constant.SysConstant;
import com.fun.client.result.R;
import com.fun.client.utils.JwtUtils;
import com.fun.client.utils.ServletUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 鉴权
 *
 * @author DJun 2020-10-04 23:48:55
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private final PublicKeyProperties prop;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, PublicKeyProperties prop) {
        super(authenticationManager);
        this.prop = prop;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader(SysConstant.HEADER_TOKEN);
        // 验证 Token 格式
        if (header == null || !header.startsWith(SysConstant.TOKEN_PREFIX)) {
            ServletUtils.renderString(response, JSONObject.toJSONString(R.unauthorized()));
            return;
        }
        // Token 鉴权
        String token = header.replace(SysConstant.TOKEN_PREFIX, "");
        String loginName;
        List<SimpleGrantedAuthority> authorities;
        try {
            loginName = JwtUtils.getLoginName(token, prop.getPublicKey());
            if (loginName == null) {
                ServletUtils.renderString(response, JSONObject.toJSONString(R.unauthorized()));
                return;
            }
            authorities = JwtUtils.getRoles(token, prop.getPublicKey());
        } catch (Exception e) {
            ServletUtils.renderString(response, JSONObject.toJSONString(R.unauthorized()));
            return;
        }

        UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(loginName, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }

}
