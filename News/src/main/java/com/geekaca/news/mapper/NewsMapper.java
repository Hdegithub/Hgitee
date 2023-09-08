package com.geekaca.news.mapper;

import com.geekaca.news.domain.News;
import org.apache.ibatis.annotations.Mapper;

/**
* @author kanata
* @description 针对表【tb_news】的数据库操作Mapper
* @createDate 2023-09-08 15:24:32
* @Entity com.geekaca.news.domain.News
*/
@Mapper
public interface NewsMapper {

    int deleteByPrimaryKey(Long id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

}
