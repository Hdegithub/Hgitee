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
     * @param id
     * @return
     */
    News selectById(Long id);

    /**
     * 根据新闻标题或新闻内容 查询新闻
     * @param news
     * @return
     */
    List<News> selectByName(News news);
}
