package com.xxxx.server.controller;

import com.xxxx.server.pojo.Admin;
import com.xxxx.server.pojo.AdminLoginParam;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Api(tags="loginController")
@RestController
public class LoginController {
    @Autowired//自动注入service
    private IAdminService adminService;
    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public RespBean login (@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request){//之所以用AdminLoginParam代替Admin,是因为Admin中有很多用不到的属性
        return adminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),
                adminLoginParam.getCode(),request);
        //服务端的处理接口参数使用：@RequestBody注解，就会把客户参数当成一个javaBean进行耦合字段赋值，简单的说就是，把客户的json对象转换为javaBean对象
        //http://localhost:8081/doc.html#/default/loginController/loginUsingPOST界面那就是发送json数据了,
    }

    @ApiOperation(value = "获取当前用户的信息")
    @PostMapping("/admin/info")
    public Admin getAdminInfo(Principal principal){//Principal是security中的, 在AdminServiceImpl.java更新了
              if(null==principal){
                  return null;
              }
              String username = principal.getName();
              Admin admin = adminService.getAdminByUserName(username);
              admin.setPassword(null);//不给前端密码
              admin.setRoles(adminService.getRoles(admin.getId()));
              return admin;
    }
    @ApiOperation(value="退出登录")//退出登陆的原理是告诉前端200,前端则会把header中的token删除,
    @PostMapping("/logout")
    public  RespBean logout(){
        return RespBean.success("注销成功!!!!!");
    }
}
