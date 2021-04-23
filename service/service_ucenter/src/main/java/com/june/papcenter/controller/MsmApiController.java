package com.june.papcenter.controller;

import com.june.commonutils.Ret;
import com.june.papcenter.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/papmsm/msm")
@CrossOrigin //跨域
public class MsmApiController {

    @Autowired
    private MsmService msmService;

    // 发送验证码的方法
    @GetMapping(value = "/send/{email}")
    public Ret sendMsm(@PathVariable String email) {
//        String code = RandomUtil.getSixBitRandom();
        String code = "123456";
        Map<String, Object> param = new HashMap<>();
        param.put("code", code);
        boolean isSend = msmService.send(param, email);
        isSend = true; // 测试使用
        if (isSend) {
            return Ret.ok();
        } else {
            return Ret.error().message("验证码发送失败");
        }
    }
}
