package com.geekaca.news.mapper;

import com.geekaca.news.domain.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author kanata
* @description 针对表【tb_news】的数据库操作Mapper
* @createDate 2023-09-08 15:24:32
* @Entity com.geekaca.news.domain.News
*/
// TODO:添加@Mapper
@Mapper
public interface NewsMapper {

    int deleteByPrimaryKey(Long id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

    // 前台页面 新闻列表查询 泛型约束
    List<News> selectAll();

    News selectById(Long id);

    //模糊查询 根据
    List<News> selectByName(News news);

    //获取新闻列表
    List<News> findNewsList();

}
