package com.fun.auth.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员表(aggregate)
 *
 * @author MrDJun 2020-09-28
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UmsMember implements Serializable{
    private static final long serialVersionUID = 1L;

    @TableId(value = "member_id", type = IdType.AUTO)
    private Long memberId;
    /**  对外开放id */
    private String openId;
    /** 账号 */
    private String loginName;
    /**  姓名 */
    private String realName;
    /** 昵称 */
    private String nickName;
    /** 头像 */
    private String avatarUrl;
    /** 手机号码 */
    private String phone;
    /** 邀请码  */
    private String inviteCode;
    /** 积分  */
    private Long integration;
    /** 成长值 */
    private Long growth;
    /** 性别:0->男；1->女；2->未知 */
    private String gender;
    /**  删除状态 0:未删；1:删除 */
    private String isDelete;
    /** 生日 */
    private Date birthday;
    /** 最后login时间 */
    private Date lastLoginTime;
    /** 详细地址 */
    private String address;
    /** 密码 */
    private String password;
    /** 账号状态 */
    private String status;
}

