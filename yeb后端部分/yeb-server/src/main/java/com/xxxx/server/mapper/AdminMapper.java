package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.server.pojo.Admin;
import com.xxxx.server.pojo.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jim
 * @since 2022-11-18
 */
public interface AdminMapper extends BaseMapper<Admin> {
    /**
     * 获取所有操作员
     * @param
     * @return
     */
    List<Admin> getAllAdmins(@Param("id") Integer id, String keywords);
}
