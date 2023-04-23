package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.server.pojo.AdminRole;
import com.xxxx.server.pojo.RespBean;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jim
 * @since 2022-11-18
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    Integer addAdminRole(@Param("adminId") Integer adminId, @Param("rids") Integer[] rids);//两个参数加params注解,mybatis里的
}
