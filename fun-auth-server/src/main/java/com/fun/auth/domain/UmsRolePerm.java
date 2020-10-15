package com.fun.auth.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色权限菜单
 *
 * @author MrDJun 2020/10/1
 */
@Data
public class UmsRolePerm implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.NONE)
    private Long roleId;

    @TableId(type = IdType.NONE)
    private Long permId;
}