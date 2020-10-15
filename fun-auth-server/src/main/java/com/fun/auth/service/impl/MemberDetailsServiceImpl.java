package com.fun.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fun.auth.constant.SysConstant;
import com.fun.auth.domain.*;
import com.fun.auth.service.*;
import com.fun.auth.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MemberDetailsServiceImpl implements IMemberDetailsService {
    @Autowired
    private IUmsMemberService memberService;
    @Autowired
    private IUmsRoleService roleService;
    @Autowired
    private IUmsMemberRoleService memberRoleService;
    @Autowired
    private IUmsPermService permService;
    @Autowired
    private IUmsRolePermService rolePermService;


    /**
     * 通过用户登录账号查询用户相关信息
     *
     * @param s loginName
     * @return UserDetails
     * @throws UsernameNotFoundException e
     */
    @Override
    public UserDetails loadUserByUsername(String s) {
        UmsMember member = memberService.findByLoginName(s);
        if (member == null) {
//            log.info("未查询到账号为'" + s + "'的用户");
            return null;
        }

        // memberId -> roleId
        List<UmsMemberRole> memberRoleList = memberRoleService.list(
                new QueryWrapper<UmsMemberRole>().eq("member_id", member.getMemberId())
        );

        List<UmsRole> authorities = new ArrayList<>();
        Set<String> permissions = new HashSet<>();

        memberRoleList.forEach(memberRole -> {

            UmsRole role = roleService.getById(memberRole.getRoleId());
            if (role.getIsDelete().equals(SysConstant.IS_NOT_DELETE) && role.getStatus().equals(SysConstant.NORMAL)) {
                authorities.add(role);
            }

            List<UmsRolePerm> perms = rolePermService.list(
                    new QueryWrapper<UmsRolePerm>().select("perm_id").eq("role_id", memberRole.getRoleId())
            );
            perms.forEach(rolePerm -> {
                UmsPerm perm = permService.getById(rolePerm.getPermId());
                if (perm.getStatus().equals(SysConstant.NORMAL)) {
                    permissions.add(perm.getPerm());
                }
            });
        });


        if (member.getStatus().equals(SysConstant.FORBIDDEN)) {
            throw new DisabledException(MessageUtils.message("member.login.is.forbidden"));
        } else if (member.getIsDelete().equals(SysConstant.IS_DELETE)) {
            throw new LockedException(MessageUtils.message("member.login.is.lock"));
        }

        return MemberDetails.builder()
                .loginName(member.getLoginName())
                .password(member.getPassword())
                .status(member.getStatus())
                .isDelete(member.getIsDelete())
                .authorities(authorities)
                .permissions(permissions)
                .build();
    }
}
