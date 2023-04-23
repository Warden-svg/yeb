package com.xxxx.server.config.exception;


import com.xxxx.server.pojo.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理
 */
@RestControllerAdvice//控制器的增强类, 拦截特定异常
public class GlobalExeception {
     @ExceptionHandler(SQLException.class)//处理所有的sql语句异常
    public RespBean mySqlException(SQLException e)
     {
         if(e instanceof SQLIntegrityConstraintViolationException){//该异常在删除职位信息中id为1的记录时出现,因为该记录作为了其他表的外键,
          return   RespBean.error("该数据有关联数据,删除失败!");       //如果不写该异常处理,则默认异常处理为Internal Server Error",
         }
         return   RespBean.error("数据库操作异常,删除失败!");
     }
}
