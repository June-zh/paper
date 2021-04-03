package com.june.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.june.commonutils.Ret;
import com.june.eduservice.entity.EduTeacher;
import com.june.eduservice.entity.vo.TeacherQuery;
import com.june.eduservice.service.EduTeacherService;
import com.june.servicebase.exceptionhandler.FbiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-04-02
 */
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired // 注入service
    private EduTeacherService teacherService;

    @GetMapping("findAll") // 查询所有列表
    public Ret findAllTeacher() {
        List<EduTeacher> list = teacherService.list(null);
        return Ret.ok().data("items", list);
    }

    // 逻辑删除的方法
    @DeleteMapping("{id}")
    public Ret removeTeacher(@PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        if (flag) {
            return Ret.ok();
        } else {
            return Ret.error();
        }
    }

    // 分页查询的方法
    // 其中current为当前页， limit为每页记录数
    @GetMapping("pageTeacher/{current}/{limit}")
    public Ret pageListTeacher(@PathVariable long current,
                             @PathVariable long limit) {
        // 创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);

        try {
            int i = 10 / 0;
        } catch (Exception e) {
            throw new FbiException(2001, "执行自定义异常");
        }


        // 调用方法实现分页
        // 底层封装，把分页所有数据封装到pageTeacher对象里面
        teacherService.page(pageTeacher,null);

        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords(); //数据list集合


        return Ret.ok().data("total",total).data("rows",records);
    }

    //4 条件查询带分页的方法
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public Ret pageTeacherCondition(@PathVariable long current,@PathVariable long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery) {
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);

        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String name = teacherQuery.getName();
//        String level = teacherQuery.getLevel().toString(); // Integer类型会报错, 统一转换成String类型
        Integer level = teacherQuery.getLevel(); // 需要导入spring的包非mybatis plus
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)) {
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create",end);
        }

        //调用方法实现条件查询分页
        teacherService.page(pageTeacher,wrapper);

        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords(); //数据list集合
        return Ret.ok().data("total",total).data("rows",records);
    }

    //添加讲师接口的方法
    @PostMapping("addTeacher")
    public Ret addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = teacherService.save(eduTeacher);
        if(save) {
            return Ret.ok();
        } else {
            return Ret.error();
        }
    }

    //根据讲师id进行查询
    @GetMapping("getTeacher/{id}")
    public Ret getTeacher(@PathVariable String id) {
        EduTeacher eduTeacher = teacherService.getById(id);
        return Ret.ok().data("teacher",eduTeacher);
    }

    //讲师修改功能
    @PostMapping("updateTeacher")
    public Ret updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean flag = teacherService.updateById(eduTeacher);
        if(flag) {
            return Ret.ok();
        } else {
            return Ret.error();
        }
    }
}

