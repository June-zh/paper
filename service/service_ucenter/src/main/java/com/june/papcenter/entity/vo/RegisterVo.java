package com.june.papcenter.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="登录注册对象", description="登录注册对象")
public class RegisterVo {
    @ApiModelProperty(value = "昵称")
    private String name;

    @ApiModelProperty(value = "邮箱号")
    private String email;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String code;

    @ApiModelProperty(value = "身份 1普通用户 2VIP用户")
    private Integer level;

}
