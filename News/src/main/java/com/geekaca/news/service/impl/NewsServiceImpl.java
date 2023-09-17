package com.geekaca.news.service.impl;

import com.geekaca.news.domain.News;
import com.geekaca.news.domain.NewsTag;
import com.geekaca.news.domain.NewsTagRelation;
import com.geekaca.news.mapper.NewsMapper;
import com.geekaca.news.mapper.NewsTagMapper;
import com.geekaca.news.mapper.NewsTagRelationMapper;
import com.geekaca.news.service.NewsService;
import com.geekaca.news.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private NewsTagMapper tagMapper;
    @Autowired
    private NewsTagRelationMapper tagRelationMapper;

    @Override
    public String saveNews(News news) {
        //1.向新闻表增加数据
        //2.向新闻和标签关联表   插入数据
        int insertSelective = newsMapper.insertSelective(news);
        List<NewsTag> newsTags1 = tagMapper.searchAll();
        Map<String, Integer> tagmap = new HashMap<>();
        String newsTags = news.getNewsTags();
        for (int i = 0; i < newsTags1.size(); i++) {
            NewsTag tag = newsTags1.get(i);
            tagmap.put(tag.getTagName(), tag.getTagId());
        }
        String[] tags = newsTags.split(",");
        for (int i = 0; i < tags.length; i++) {
            String tagName = tags[i];
            boolean isExsist = false;
            //发送sql，查询tag是否已经存在数据库中
            //如果存在就获取对应的tagId,用来插入关联表
            Integer tagId = tagmap.get(tagName);
            if (tagId == null) {
                NewsTag newsTag=new NewsTag();
                newsTag.setTagName(tagName);
                tagMapper.insertSelective(newsTag);
                tagId=newsTag.getTagId();
            }
            NewsTagRelation newsTagRelation=new NewsTagRelation();
            newsTagRelation.setNewsId(news.getNewsId());
            newsTagRelation.setTagId(tagId);
            tagRelationMapper.insertSelective(newsTagRelation);
            //如果不存在，说明是新的标签，插入标签表tb_news_tag,获取生产的tagId，插入关联表
        }
        return null;
    }

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

    public List<News> findNewsList() {
        List<News> newsList = newsMapper.findNewsList();
        return newsList;
    }

    @Override
    public PageResult getPageNews(Integer pageNo, Integer pageSize) {
        int start = (pageNo - 1) * pageSize;
        List<News> newsList = newsMapper.selectByPage(start, pageSize);
        int count = newsMapper.selectNewsCount();
        PageResult pageResult = new PageResult(newsList,count,pageSize,pageNo);
        return pageResult;
    }
}
