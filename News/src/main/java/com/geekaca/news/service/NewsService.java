package com.geekaca.news.service;

import com.geekaca.news.domain.News;


import java.util.List;

public interface NewsService {
    /**
     * 前台页面 新闻列表查询
     * @return
     */
    List<News> selectAll();

    /**
     * 根据id查询新闻 带出评论
     * @param newsId
     * @return
     */
    News selectById(Long newsId);

    /**
     * 根据新闻标题或新闻内容 查询新闻
     * @param news
     * @return
     */
    List<News> selectByName(News news);

    /**
     * 后台 获取新闻列表
     * @return
     */
    List<News> findNewsList();
}
