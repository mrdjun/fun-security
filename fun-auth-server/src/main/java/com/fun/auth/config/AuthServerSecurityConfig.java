package com.fun.auth.config;

import com.fun.auth.constant.SysConstant;
import com.fun.auth.config.handler.JwtAccessDeniedHandler;
import com.fun.auth.config.handler.JwtAuthenticationHandler;
import com.fun.auth.config.properties.NoAuthProperties;
import com.fun.auth.config.properties.RsaKeyProperties;
import com.fun.auth.filter.JwtAuthenticationFilter;
import com.fun.auth.filter.JwtAuthorizationFilter;
import com.fun.auth.service.IMemberDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 *
 * @author MrDJun
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class AuthServerSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IMemberDetailsService memberDetailsService;
    @Autowired
    private RsaKeyProperties rsaKeyProperties;
    @Autowired
    private NoAuthProperties noAuthProperties;

    public AuthServerSecurityConfig() {}

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    /**
     * 指定认证对象的来源
     *
     * @param auth AuthenticationManagerBuilder
     * @throws Exception e
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                // allow anonymous resource requests
                .antMatchers(noAuthProperties.getUrls());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(SysConstant.LOGIN_URI).permitAll()
                .anyRequest().authenticated()

                // jwtLogin(认证授权) 和 jwtVerify(鉴权) 拦截器
                .and()
                .addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilter(new JwtAuthenticationFilter(super.authenticationManager(), rsaKeyProperties))
                .addFilter(new JwtAuthorizationFilter(super.authenticationManager(), rsaKeyProperties))

                // 认证失败和授权失败处理
                .exceptionHandling()
                .authenticationEntryPoint(new JwtAuthenticationHandler())
                .accessDeniedHandler(new JwtAccessDeniedHandler())

                // 完全关闭Session策略并且不使用
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // 防止页面被 frame
                .and()
                .headers()
                .frameOptions().sameOrigin();
    }

}
