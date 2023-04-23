package com.xxxx.server.controller;


import com.xxxx.server.pojo.Position;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.IPositionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jim
 * @since 2022-11-18
 */
@RestController
@RequestMapping("/system/basic/pos")//必须是/system/config,通过权限控制来使得只能是存在于表中的
public class PositionController {
    @Autowired
    private IPositionService positionService;
    @ApiOperation(value = "获取所有职位信息")
    @GetMapping("/")
    public List<Position> getAllPositions(){
    return positionService.list();//list()是mybatisplus封装好的方法,位于IPositionService所继承的父类里
        //由于是单表的增删改查,   加上mybatisplus,  甚至不需要写service和mapper
}
    @ApiOperation(value="添加职位信息")
    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position){
        position.setCreateDate(LocalDateTime.now());
        if (positionService.save(position)){
            return RespBean.success("添加成功");
        }else {
            return RespBean.error("添加失败");
        }
    }
    @ApiOperation(value="更新职位信息")
    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position){
        if(positionService.updateById(position)){
            return RespBean.success("更新成功！");
        }else{
            return RespBean.error("更新失败！");
        }
    }

    @ApiOperation(value="删除职位信息")
    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable Integer id){
        if(positionService.removeById(id)){
            return RespBean.success("删除成功！");
        }else{
            return RespBean.error("删除失败");
        }
    }

    @ApiOperation(value="批量删除职位")
    @DeleteMapping("/")
    public RespBean deletePositionsByIds(Integer[] ids){
        if(positionService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("批量删除成功");
        }else{
            return RespBean.error("批量删除失败");
        }
    }
}
