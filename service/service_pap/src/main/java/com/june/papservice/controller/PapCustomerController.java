package com.june.papservice.controller;


import com.june.commonutils.Ret;
import com.june.papservice.entity.PapCustomer;
import com.june.papservice.entity.vo.CustomerQuery;
import com.june.papservice.service.PapCustomerService;
import com.june.servicebase.exceptionhandler.FbiException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 客户 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-02-24
 */
@Deprecated
@Api(description="客户管理")
@RestController
@RequestMapping("/papservice/customer")
@CrossOrigin
public class PapCustomerController {

    // 访问地址： http://localhost:8001/papservice/customer/findAll
    // 把service注入
    @Autowired
    private PapCustomerService customerService;

    // 查询客户表所有数据
    @ApiOperation(value = "所有客户列表")
    @GetMapping("findAll")
    public Ret findAllCustomer() {
        // 调用service的方法实现查询所有的操作
        List<PapCustomer> list = customerService.list(null);
        return Ret.ok().data("items",list);
    }

    // 逻辑删除客户的方法
    @ApiOperation(value = "逻辑删除客户")
    @DeleteMapping("{id}")
    public Ret removeCustomer(@ApiParam(name = "id", value = "客户ID", required = true)
                           @PathVariable String id) {
        boolean flag = customerService.removeById(id);
        if(flag) {
            return Ret.ok();
        } else {
            return Ret.error();
        }
    }

    // 分页查询客户的方法
    // current 当前页
    // limit 每页记录数
    @GetMapping("pageCustomer/{current}/{limit}")
    public Ret pageListCustomer(@PathVariable long current,
                             @PathVariable long limit) {
        // 创建page对象
        Page<PapCustomer> pageCustomer = new Page<>(current,limit);

        try {
            int i = 10/0;
        }catch(Exception e) {
            //执行自定义异常
            throw new FbiException(20001,"执行了自定义异常处理....");
        }


        // 调用方法实现分页
        // 底层封装，把分页所有数据封装到pageCustomer对象里面
        customerService.page(pageCustomer,null);

        long total = pageCustomer.getTotal(); // 总记录数
        List<PapCustomer> records = pageCustomer.getRecords(); // 数据list集合

        return Ret.ok().data("total",total).data("rows",records);
    }

    // 条件查询带分页的方法
    @PostMapping("pageCustomerCondition/{current}/{limit}")
    public Ret pageCustomerCondition(@PathVariable long current,@PathVariable long limit,
                                  @RequestBody(required = false) CustomerQuery customerQuery) {
        // 创建page对象
        Page<PapCustomer> pageCustomer = new Page<>(current,limit);

        // 构建条件
        QueryWrapper<PapCustomer> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // 动态sql
        String name = customerQuery.getName();
        Integer level = customerQuery.getLevel();
        String begin = customerQuery.getBegin();
        String end = customerQuery.getEnd();
        // 判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)) {
            // 构建条件
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

        // 排序
        wrapper.orderByDesc("gmt_create");

        // 调用方法实现条件查询分页
        customerService.page(pageCustomer,wrapper);

        long total = pageCustomer.getTotal();//总记录数
        List<PapCustomer> records = pageCustomer.getRecords(); //数据list集合
        return Ret.ok().data("total",total).data("rows",records);
    }

    // 添加客户接口的方法
    @PostMapping("addCustomer")
    public Ret addCustomer(@RequestBody PapCustomer papCustomer) {
        boolean save = customerService.save(papCustomer);
        if (save) {
            return Ret.ok();
        } else {
            return Ret.error();
        }
    }

    // 根据客户id进行查询
    @GetMapping("getCustomer/{id}")
    public Ret getCustomer(@PathVariable String id) {
        PapCustomer papCustomer = customerService.getById(id);
        return Ret.ok().data("customer", papCustomer);
    }

    // 客户修改功能
    @PostMapping("updateCustomer")
    public Ret updateCustomer(@RequestBody PapCustomer papCustomer) {
        boolean flag = customerService.updateById(papCustomer);
        if(flag) {
            return Ret.ok();
        } else {
            return Ret.error();
        }
    }
}

