package com.fun.auth.domain;

import com.fun.auth.constant.SysConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * 会员详细信息
 *
 * @author MrDJun 2020/10/4
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDetails implements UserDetails {
    private String loginName;

    private String password;

    private String status;

    private String isDelete;

    private Collection<? extends GrantedAuthority> authorities;

    private Set<String> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.loginName;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberDetails user = (MemberDetails) o;
        return loginName.equals(user.loginName);
    }
}
