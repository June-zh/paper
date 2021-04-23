package com.june.papcms.controller;


import com.june.commonutils.Ret;
import com.june.papcms.entity.PapBanner;
import com.june.papcms.service.PapBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前台
 * </p>
 *
 * @author testjava
 * @since 2021-04-15
 */
@RestController
@RequestMapping("/papcms/userbanner")
@CrossOrigin
public class UserBannerController {

    @Autowired
    private PapBannerService bannerService;

    // 查询所有banner
    @GetMapping("getAllBanner")
    public Ret getAllBanner() {
        List<PapBanner> list = bannerService.selectAllBanner();
        return Ret.ok().data("list", list);
    }
}

