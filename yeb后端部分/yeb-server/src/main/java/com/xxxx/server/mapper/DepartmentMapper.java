package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.server.pojo.Department;
import com.xxxx.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jim
 * @since 2022-11-18
 */
public interface DepartmentMapper extends BaseMapper<Department> {
    /**
     * 获取所有部门
     * @return
     */
    List<Department> getAllDepartments();
    /**
     * 添加部门
     * @return
     */

    void addDep(Department dep);
    /**
     * 删除部门
     * @return
     */

    void delDep(Department dep);

}
