package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.server.pojo.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jim
 * @since 2022-11-18
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户id查询菜单列表
     * @return
     */
    List<Menu> getMenusByAdminId(Integer id);
    /**
     * 根据用户角色查询菜单列表
     * @return
     */
    List<Menu> getMenusWithRole();
    /**
     * 查询所有菜单
     * @return
     */
    List<Menu> getAllMenus();
}
