package com.june.papcenter.controller;


import com.june.commonutils.JwtUtils;
import com.june.commonutils.Ret;
import com.june.papcenter.entity.PapCustomer;
import com.june.papcenter.entity.vo.RegisterVo;
import com.june.papcenter.service.PapCustomerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 客户 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-04-18
 */
@RestController
@RequestMapping("/papcenter/customer")
@CrossOrigin
public class PapCustomerController {
    @Autowired
    private PapCustomerService customerService;

    // 登录
    @PostMapping("login")
    @ApiOperation(value = "会员登录")
    public Ret loginUser(@RequestBody PapCustomer customer) {
        // 调用service方法实现登录
        // 返回token，用jwt生成
        String token = customerService.login(customer);
        return Ret.ok().data("token", token);
    }

    // 注册
    @PostMapping("register")
    @ApiOperation(value = "会员注册")
    public Ret registerUser(@RequestBody RegisterVo registerVo) {
        customerService.register(registerVo);
        return Ret.ok();
    }

    // 根据token获取用户信息
    @GetMapping("getLoginInfo")
    @ApiOperation(value = "获取token")
    public Ret getCustomerInfo(HttpServletRequest request) {
        String customerId = JwtUtils.getCustomerIdByJwtToken(request);
        // 根据ID获取信息
        PapCustomer customer = customerService.getById(customerId);
        return Ret.ok().data("userInfo", customer);
    }
}

