package com.geekaca.news.service;

import com.geekaca.news.domain.AdminUser;

public interface AdminUserService {
    /**
     * 登录
     * @param userName
     * @return 登陆成功后 获取用户信息 登录失败 null
     */
    AdminUser login(String userName ,String password);

    /**
     * 获取用户信息
     *
     * @param loginUserId
     * @return
     */
    AdminUser getUserDetailById(Integer loginUserId);

    /**
     * 修改当前登录用户的密码
     *
     * @param loginUserId
     * @param originalPassword
     * @param newPassword
     * @return
     */
    Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword);

    /**
     * 修改当前登录用户的名称信息
     *
     * @param loginUserId
     * @param loginUserName
     * @param nickName
     * @return
     */
    Boolean updateName(Integer loginUserId, String loginUserName, String nickName);
}
