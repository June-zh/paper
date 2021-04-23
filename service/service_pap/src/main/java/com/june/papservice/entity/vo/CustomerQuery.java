package com.june.papservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CustomerQuery {

    @ApiModelProperty(value = "客户名称,模糊查询")
    private String name;

    @ApiModelProperty(value = "头衔 1普通用户 2VIP用户")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间", example = "2021-01-01 10:10:10")
    private String begin; // 此处使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间", example = "2021-12-01 10:10:10")
    private String end;
}
