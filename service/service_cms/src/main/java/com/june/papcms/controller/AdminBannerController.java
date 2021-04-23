package com.june.papcms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.june.commonutils.Ret;
import com.june.papcms.entity.PapBanner;
import com.june.papcms.service.PapBannerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 后台
 * </p>
 *
 * @author testjava
 * @since 2021-04-15
 */
@RestController
@RequestMapping("/papcms/adminbanner")
@CrossOrigin
public class AdminBannerController {
    @Autowired
    private PapBannerService bannerService;
    @ApiOperation(value = "获取Banner分页列表")
    @GetMapping("{page}/{limit}")
    public Ret index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit) {
        Page<PapBanner> pageParam = new Page<>(page, limit);
        bannerService.page(pageParam,null);
        return Ret.ok().data("items", pageParam.getRecords()).data("total",pageParam.getTotal());
    }
    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public Ret get(@PathVariable String id) {
        PapBanner banner = bannerService.getById(id);
        return Ret.ok().data("item", banner);
    }
    @ApiOperation(value = "新增Banner")
    @PostMapping("save")
    public Ret save(@RequestBody PapBanner banner) {
        bannerService.save(banner);
        return Ret.ok();
    }
    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public Ret updateById(@RequestBody PapBanner banner) {
        bannerService.updateById(banner);
        return Ret.ok();
    }
    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public Ret remove(@PathVariable String id) {
        bannerService.removeById(id);
        return Ret.ok();
    }
}

