package com.fun.auth.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户角色
 *
 * @author MrDJun 2020-09-28
 */
@Data
public class UmsMemberRole implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.NONE)
    private Long memberId;

    @TableId(type = IdType.NONE)
    private Long roleId;
}
