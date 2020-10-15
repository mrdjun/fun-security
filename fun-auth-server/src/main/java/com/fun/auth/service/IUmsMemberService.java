package com.fun.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fun.auth.domain.UmsMember;

/**
 * @author MrDJun 2020/10/1
 */
public interface IUmsMemberService extends IService<UmsMember> {
    UmsMember findByLoginName(String s);
}
