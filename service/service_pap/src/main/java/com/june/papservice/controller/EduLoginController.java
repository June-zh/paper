package com.june.eduservice.controller;

import com.june.commonutils.Ret;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin // 解决跨域访问
public class EduLoginController {

    @PostMapping("login")
    public Ret login() {
        return Ret.ok().data("token", "admin");
    }

    @GetMapping("info")
    public Ret info() {
        return Ret.ok().data("roles", "[admin]").data("name", "admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
