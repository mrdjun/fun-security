package com.fun.auth.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 登录 DTO
 *
 * @author MrDJun 2020/10/6
 */
@Data
public class MemberLoginDto {

    @NotNull(message = "登录账号不能为空")
    @Size(min = 4, max = 25, message = "登录的账号长度超出了限制")
    private String loginName;

    @NotNull(message = "登录密码不能为空")
    @Size(min = 4, max = 30, message = "密码长度超出限制")
    private String password;

    private Boolean rememberMe = false;
}
