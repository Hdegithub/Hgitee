package com.geekaca.news.mapper;

import com.geekaca.news.domain.NewsComment;
import com.geekaca.news.utils.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author kanata
* @description 针对表【tb_news_comment】的数据库操作Mapper
* @createDate 2023-09-08 15:24:32
* @Entity com.geekaca.news.domain.NewsComment
*/
@Mapper
public interface NewsCommentMapper {

    int deleteByPrimaryKey(Long id);

    int insert(NewsComment record);

    int insertSelective(NewsComment record);

    NewsComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NewsComment record);

    int updateByPrimaryKey(NewsComment record);

    List<NewsComment> findNewsCommentList(PageBean pageBean);
}
