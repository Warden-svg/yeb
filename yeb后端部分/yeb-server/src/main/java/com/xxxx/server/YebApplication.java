package com.xxxx.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

//@ComponentScan(basePackages = {"com.xxxx.server.mapper"})//弹幕说的循环依赖
@SpringBootApplication
@MapperScan("com.xxxx.server.mapper")//扫描到com.xxxx.server.mapper目录下
@EnableScheduling//开启MailTask定时任务
public class YebApplication {
    public static void main(String[] args){
        SpringApplication.run(YebApplication.class,args);
    }
}
