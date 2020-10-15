package com.fun.auth.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

/**
 * 角色
 * @author MrDJun 2020/10/1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UmsRole extends BaseEntity implements GrantedAuthority {

    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;
    /** 角色名称 */
    private String roleName;
    /** 角色标识字符串 */
    @JsonIgnore
    private String roleKey;
    /** 当前角色是否被删除 */
    private String isDelete;

    @Override
    public String getAuthority() {
        return roleKey;
    }
}