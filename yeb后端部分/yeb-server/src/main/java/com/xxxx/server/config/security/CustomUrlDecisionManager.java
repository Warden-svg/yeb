package com.xxxx.server.config.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Author: xxxx
 * @Description: 权限控制 , 判断用户角色
 * @Params:
 */

@Component
public class CustomUrlDecisionManager implements AccessDecisionManager {


    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute configAttribute : configAttributes) {
            //ConfigAttribute=访问当前url所需的角色     在customFilter中配置的
            String needRole = configAttribute.getAttribute();
            //判断角色是否为登录即可访问的角色,ROLE_LOGIN
            if ("ROLE_LOGIN".equals(needRole)){
                //判断登录
                if (authentication instanceof AnonymousAuthenticationToken){
                    throw new AccessDeniedException("CustomUrlDecisionManager : 尚未登录,请登录!");
                }else {
                    //返回放行
                    return;
                }
            }
            //判断角色是否为url所需角色(通过后台拿到用户角色们和ConfigAttribute进行比对)
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            //一个用户有多重角色authorities
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(needRole)){
                    //返回放行
                    return;
                }
            }
        }
        throw new AccessDeniedException("CustomUrlDecisionManager : 权限不足!!!请联系管理员!");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
