package com.fun.auth.filter;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fun.auth.constant.SysConstant;
import com.fun.auth.domain.MemberLoginDto;
import com.fun.auth.domain.MemberDetails;
import com.fun.auth.result.R;
import com.fun.auth.utils.ServletUtils;
import com.fun.auth.utils.JwtUtils;
import com.fun.auth.config.properties.RsaKeyProperties;
import com.fun.auth.utils.MessageUtils;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 认证
 *
 * @author MrDJun 2020-10-03
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final ThreadLocal<Boolean> rememberMe = new ThreadLocal<>();
    private final AuthenticationManager authenticationManager;
    private final RsaKeyProperties prop;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, RsaKeyProperties prop) {
        this.authenticationManager = authenticationManager;
        this.prop = prop;
        // 重写登录 URI
        super.setFilterProcessesUrl(SysConstant.LOGIN_URI);
    }

    @Override
    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        MemberLoginDto login = new ObjectMapper().readValue(request.getInputStream(), MemberLoginDto.class);
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(login.getLoginName(), login.getPassword());
        rememberMe.set(login.getRememberMe());
        return authenticationManager.authenticate(authRequest);
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                         FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        MemberDetails member = (MemberDetails) authResult.getPrincipal();
        Set<String> permissions = member.getPermissions();
        Set<String> authorities = member.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        String token = JwtUtils.createToken(permissions, authorities, member.getLoginName(), prop.getPrivateKey(), rememberMe.get());
        rememberMe.remove();
        response.addHeader(SysConstant.HEADER_TOKEN, SysConstant.TOKEN_PREFIX + token);
        ServletUtils.renderString(response, JSONObject.toJSONString(R.success(true, MessageUtils.message("member.login.success"))));
    }

}
