package com.june.papcms.service;

import com.june.papcms.entity.PapBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-04-15
 */
public interface PapBannerService extends IService<PapBanner> {

    // 查询所有banner
    List<PapBanner> selectAllBanner();
}
