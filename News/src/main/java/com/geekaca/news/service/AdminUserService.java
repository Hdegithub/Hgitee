package com.geekaca.news.service;

import com.geekaca.news.domain.AdminUser;

public interface AdminUserService {
    /**
     * 登录
     * @param userName
     * @return 登陆成功后 获取用户信息 登录失败 null
     */
    AdminUser login(String userName ,String password);
}
