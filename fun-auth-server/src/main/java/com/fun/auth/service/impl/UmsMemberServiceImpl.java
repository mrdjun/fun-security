package com.fun.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fun.auth.domain.UmsMember;
import com.fun.auth.mapper.UmsMemberMapper;
import com.fun.auth.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author MrDJun 2020/9/29
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements IUmsMemberService {
    @Autowired
    private UmsMemberMapper memberMapper;

    @Override
    public UmsMember findByLoginName(String s) {
        return memberMapper.selectOne(new QueryWrapper<UmsMember>().eq("login_name", s));
    }
}
