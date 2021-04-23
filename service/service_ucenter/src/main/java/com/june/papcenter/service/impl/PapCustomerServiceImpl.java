package com.june.papcenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.june.commonutils.JwtUtils;
import com.june.papcenter.entity.PapCustomer;
import com.june.papcenter.entity.vo.RegisterVo;
import com.june.papcenter.mapper.PapCustomerMapper;
import com.june.papcenter.service.PapCustomerService;
import com.june.servicebase.exceptionhandler.FbiException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 客户 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-04-18
 */
@Service
public class PapCustomerServiceImpl extends ServiceImpl<PapCustomerMapper, PapCustomer> implements PapCustomerService {

    // 登录的方法
    @Override
    public String login(PapCustomer customer) {
        // 获取邮箱号和密码
        String email = customer.getEmail();
        String password = customer.getPassword();

        // 邮箱号和密码非空的判断
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            throw new FbiException(2001, "登录失败");
        }

        // 判断email是否正确
        QueryWrapper<PapCustomer> wrapper = new QueryWrapper<>();
        wrapper.eq("email", email);
        PapCustomer emailCustomer = baseMapper.selectOne(wrapper);
        if (emailCustomer == null) {
            throw new FbiException(2001, "登录失败");
        }

        // 判断密码
        /* TODO 一般使用MD5加密密码，不直接存储原文到数据库 */
        if (!password.equals(emailCustomer.getPassword())) {
            throw new FbiException(2001, "登录失败");
        }

        // 登录成功
        return JwtUtils.getJwtToken(emailCustomer.getId(), emailCustomer.getName());
    }

    // 注册的方法
    @Override
    public void register(RegisterVo registerVo) {

        // 获取注册的数据
        String code = registerVo.getCode();
        String email = registerVo.getEmail();
        String password = registerVo.getPassword();
        String name = registerVo.getName();
        Integer level = 1; // 新用户默认为普通用户



        // 非空判断
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password) || StringUtils.isEmpty(name) || StringUtils.isEmpty(code)) {
            throw new FbiException(2001, "注册失败");
        }

        // 判断验证码
        String emailCode = "123456"; // 验证码暂时为123456
        if (!code.equals(emailCode)) {
            throw new FbiException(2001, "注册失败");
        }

        // 判断email是否重复
        QueryWrapper<PapCustomer> wrapper = new QueryWrapper<>();
        wrapper.eq("email", email);
        Integer count = baseMapper.selectCount(wrapper); // 是否存在，存在则大于0
        if (count > 0) {
            throw new FbiException(2001, "注册失败");
        }

        // 添加数据
        PapCustomer customer = new PapCustomer();
        customer.setEmail(email);
        customer.setName(name);
        customer.setPassword(password);
        customer.setLevel(level);
        customer.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS 4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        baseMapper.insert(customer);
    }
}
