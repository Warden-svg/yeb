package com.xxxx.server.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxxx.server.pojo.RespBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: xxxx
 * @Description: 当未登录/或/token 失效时，访问接口时自定义的返回结果
 * @Params: 类
 */
@Component
public class RestAuthorizationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        //1.java.io.PrintWriter是java中很常见的一个类，该类可用来创建一个文件并向文本文件写入数据。可以理解为java中的文件输出，java中的文件输入则是java.io.File。
        RespBean bean = RespBean.error("尚未登录，请登录！");//error方法最终返回一个new完的ResBean类对象
        bean.setCode(401);
        writer.write(new ObjectMapper().writeValueAsString(bean));
        writer.flush();
        writer.close();

    }
}
