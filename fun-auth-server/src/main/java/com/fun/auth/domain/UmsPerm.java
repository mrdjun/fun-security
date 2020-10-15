package com.fun.auth.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 会员权限
 *
 * @author MrDJun 2020/10/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UmsPerm extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long permId;
    /** 权限关键字 */
    private String perm;
    /** 权限名称 */
    private String permName;
}
