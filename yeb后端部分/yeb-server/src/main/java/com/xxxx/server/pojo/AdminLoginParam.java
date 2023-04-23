package com.xxxx.server.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用来接收登录用户的基本信息
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors
@ApiModel(value = "AdminLogin对象",description = "")  //Swaiger注解
public class AdminLoginParam {
    @ApiModelProperty(value = "用户名",required = true)//required必填
    private String username;
    @ApiModelProperty(value = "密码",required = true)
    private  String password;
    @ApiModelProperty(value = "验证码",required = true)
    private  String code;
}
