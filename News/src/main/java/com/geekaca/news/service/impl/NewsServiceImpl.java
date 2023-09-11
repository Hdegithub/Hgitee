package com.geekaca.news.service.impl;

import com.geekaca.news.domain.News;
import com.geekaca.news.mapper.NewsMapper;
import com.geekaca.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;

    public List<News> selectAll() {
        List<News> newsList = newsMapper.selectAll();
        return newsList;
    }

    public News selectById(Long id) {
        News news = newsMapper.selectById(id);
        return news;
    }

    public List<News> selectByName(News news) {
        List<News> newsList = newsMapper.selectByName(news);
        return newsList;
    }
}
