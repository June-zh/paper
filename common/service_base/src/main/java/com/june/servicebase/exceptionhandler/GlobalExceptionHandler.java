package com.june.servicebase.exceptionhandler;

import com.june.commonutils.Ret;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 指定异常
    @ExceptionHandler(Exception.class)
    @ResponseBody // 返回数据
    public Ret error(Exception e) {
        e.printStackTrace();
        return Ret.error().message("执行了全局异常处理");
    }

    // 特定异常

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody //为了返回数据
    public Ret error(ArithmeticException e) {
        e.printStackTrace();
        return Ret.error().message("执行了ArithmeticException异常处理..");
    }
    // 自定义异常
    @ExceptionHandler(FbiException.class)
    @ResponseBody //为了返回数据
    public Ret error(FbiException e) {
        log.error(e.getMessage());
        e.printStackTrace();

        return Ret.error().code(e.getCode()).message(e.getMsg());
    }

}
