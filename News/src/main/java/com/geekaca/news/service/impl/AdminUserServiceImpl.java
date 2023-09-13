package com.geekaca.news.service.impl;

import com.geekaca.news.domain.AdminUser;
import com.geekaca.news.mapper.AdminUserMapper;
import com.geekaca.news.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    private AdminUserMapper userMapper;

    @Override
    public AdminUser login(String userName, String password) {
        AdminUser user = userMapper.login(userName, password);
        return user;
    }
}
