package com.xxxx.server.config.security;


import com.xxxx.server.pojo.Menu;
import com.xxxx.server.pojo.Role;
import com.xxxx.server.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 权限控制(Filter实现的)
 *根据请求的URL  分析该URL请求所需的角色
 * menus表中定义了不同角色可以访问的菜单接口
 */
@Component
public class CustomFilter implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private IMenuService menuService;

    AntPathMatcher antPathMatcher = new AntPathMatcher();//spring提供的一个匹配工具类
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 获取请求的 url
        String requestUrl = ((FilterInvocation)object).getRequestUrl();
        List<Menu> menus = menuService.getMenusWithRole();
        // 判断请求的url 与菜单表中的url是否匹配
        for (Menu menu : menus) {
            if(antPathMatcher.match(menu.getUrl(),requestUrl)){
                menu.getRoles();//根据菜单拿到角色
                String[] str = menu.getRoles().stream().map(Role::getName).toArray(String[]::new);
                //把拿到的角色存起来,等着和从后台获取的用户角色对比,
                return SecurityConfig.createList(str);
            }
        }
        // 没匹配到的url,  则该请求不需要特定角色才能访问  只要是登录的用户即可访问   ROLE_LOGIN角色,是已登录的用户
        //把"ROLE_LOGIN"存起来,等着和从后台获取的用户角色对比,"ROLE_LOGIN"在customUrlDecisionManager中配置
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
