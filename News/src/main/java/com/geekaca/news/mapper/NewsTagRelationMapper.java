package com.geekaca.news.mapper;

import com.geekaca.news.domain.NewsTagRelation;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 29484
* @description 针对表【tb_news_tag_relation】的数据库操作Mapper
* @createDate 2023-09-15 08:45:06
* @Entity com.geekaca.news.domain.TbNewsTagRelation
*/
@Mapper
public interface NewsTagRelationMapper {

    int deleteByPrimaryKey(Long id);

    int insert(NewsTagRelation record);

    int insertSelective(NewsTagRelation record);

    NewsTagRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NewsTagRelation record);

    int updateByPrimaryKey(NewsTagRelation record);

}
