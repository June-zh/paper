package com.june.papcenter.service;

import com.june.papcenter.entity.PapCustomer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.june.papcenter.entity.vo.RegisterVo;

/**
 * <p>
 * 客户 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-04-18
 */
public interface PapCustomerService extends IService<PapCustomer> {

    // 登录方法
    String login(PapCustomer customer);

    // 注册
    void register(RegisterVo registerVo);
}
