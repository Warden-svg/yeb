package com.xxxx.server.config;


import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis内置的插件分页配置
 */
@Configuration
public class MyBatisPlusConfig {
    @Bean
    public PaginationInnerInterceptor paginationInterceptor(){
        return new PaginationInnerInterceptor();
    }
}
