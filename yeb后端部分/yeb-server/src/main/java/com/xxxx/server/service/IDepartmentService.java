package com.xxxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.pojo.Department;
import com.xxxx.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jim
 * @since 2022-11-18
 */
public interface IDepartmentService extends IService<Department> {
    /**
     * 获取所有部门
     * @return
     */
    List<Department> getAllDepartments();
    /**
     * 添加部门
     * @return
     */
    RespBean addDep(Department department);

    /**
     * 删除部门
     * @param id
     * @return
     */
    RespBean delDep(Integer id);
}
