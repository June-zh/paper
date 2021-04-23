package com.june.papcms.service.impl;

import com.june.papcms.entity.PapBanner;
import com.june.papcms.mapper.PapBannerMapper;
import com.june.papcms.service.PapBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-04-15
 */
@Service
public class PapBannerServiceImpl extends ServiceImpl<PapBannerMapper, PapBanner> implements PapBannerService {

    // 查询所有banner
    @Override
    public List<PapBanner> selectAllBanner() {
        List<PapBanner> list = baseMapper.selectList(null);
        return list;
    }
}
