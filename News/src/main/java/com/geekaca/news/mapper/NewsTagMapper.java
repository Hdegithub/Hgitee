package com.geekaca.news.mapper;

import com.geekaca.news.domain.News;
import com.geekaca.news.domain.NewsTag;
import com.geekaca.news.domain.TagNewsCount;
import com.geekaca.news.utils.PageQueryUtil;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 29484
* @description 针对表【tb_news_tag】的数据库操作Mapper
* @createDate 2023-09-15 08:45:06
* @Entity com.geekaca.news.domain.TbNewsTag
*/
@Mapper
public interface NewsTagMapper {

    int deleteByPrimaryKey(Long id);

    int insert(NewsTag record);

    int insertSelective(NewsTag record);

    NewsTag selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NewsTag record);

    int updateByPrimaryKey(NewsTag record);

    List<NewsTag>searchAll();

    List<TagNewsCount> selectTagNewsCounts();

    List<NewsTag> findTagList(PageQueryUtil pageUtil);

    int getTotalTags(PageQueryUtil pageUtil);

    NewsTag selectByTagName(String tagName);

    int deleteBatch(Integer[] ids);

}
