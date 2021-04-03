package com.june.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/*
*统一返回结果
 */
@Data
public class Ret {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    /*
    *将构造方法私有化
     */
    private Ret() {

    }

    /*
     *成功的静态方法
     */
    public static Ret ok() {
        Ret r = new Ret();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    /*
     *失败的静态方法
     */
    public static Ret error() {
        Ret r = new Ret();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    public Ret success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public Ret message(String message){
        this.setMessage(message);
        return this;
    }

    public Ret code(Integer code){
        this.setCode(code);
        return this;
    }

    public Ret data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public Ret data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}