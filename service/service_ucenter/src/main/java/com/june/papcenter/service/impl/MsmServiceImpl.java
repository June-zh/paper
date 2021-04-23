package com.june.papcenter.service.impl;

import com.june.papcenter.service.MsmService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
public class MsmServiceImpl implements MsmService {
    // 发送验证码的方法
    @Override
    public boolean send(Map<String, Object> param, String email) {
        if (StringUtils.isEmpty(email)) {
            return false;
        }


        return false;
    }
}
