package com.xxxx.server.utils;

import com.xxxx.server.pojo.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 自定义的操作员(Admin)工具类
 * @author kkg
 * @since 1.0.0
 */
public class AdminUtils {
    public static Admin getCurrentAdmin(){
        return (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
