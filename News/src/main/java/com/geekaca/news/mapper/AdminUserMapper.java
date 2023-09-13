package com.geekaca.news.mapper;

import com.geekaca.news.domain.AdminUser;
import org.apache.ibatis.annotations.Param;

/**
* @author kanata
* @description 针对表【tb_admin_user】的数据库操作Mapper
* @createDate 2023-09-13 14:24:28
* @Entity com.geekaca.news.domain.AdminUser
*/
public interface AdminUserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    AdminUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);

    //登录 多个参数要@Param注解
    AdminUser login(@Param("uname") String userName , @Param("pwd") String password);
}
