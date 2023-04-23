package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.server.mapper.MenuRoleMapper;
import com.xxxx.server.pojo.MenuRole;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.IMenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jim
 * @since 2022-11-18
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {
@Autowired
private MenuRoleMapper menuRoleMapper;
    @Override
    @Transactional//删除相关
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        //这一步是前后端配合,前端把修改后能访问哪些菜单一起放到了mids里, 这里把manuRole中的rid对应的菜单id全删 ,然后再吧mids全加里面去
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid",rid));//rid在manuRole表中是非主键ID,所以不能用removeById(rid)
        if(null==mids||mids.length==0){
            return RespBean.success("更新成功");
        }
        Integer result = menuRoleMapper.insertRecord(rid,mids);
        if(result == mids.length){
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败");
    }
}
