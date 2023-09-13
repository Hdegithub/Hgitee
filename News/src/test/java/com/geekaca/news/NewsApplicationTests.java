package com.geekaca.news;

import cn.hutool.crypto.SecureUtil;
import com.geekaca.news.domain.News;
import com.geekaca.news.domain.NewsComment;
import com.geekaca.news.mapper.NewsMapper;
import org.apache.catalina.security.SecurityUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class NewsApplicationTests {

    @Autowired
    private NewsMapper newsMapper;

    /**
     * 测试查询
     */
    @Test
    public void testSelectByPrimaryKey() {
        News news = newsMapper.selectByPrimaryKey(5L);
        Assertions.assertNotNull(news);
        System.out.println(news);
    }

    /**
     * 测试新增
     */
    @Test
    void testInsert(){
        News news = new News();
        news.setNewsTitle("TestInsert");
        news.setNewsSubUrl("helloBoot");
        news.setNewsCoverImage("http://localhost:28083/admin/dist/img/rand/23.jpg");
        news.setNewsContent("你好");
        news.setNewsCategoryId(24);
        news.setNewsCategoryName("日常随笔");
        news.setNewsTags("测试");
        news.setNewsStatus(1);
        news.setNewsViews(34L);
        news.setEnableComment(0);
        news.setIsDeleted(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = "2023-09-08 12:12:23";
        String time2 = "2023-09-08 17:22:13";
        try {
            Date date = sdf.parse(time);
            Date date2 = sdf.parse(time2);
            news.setCreateTime(date);
            news.setUpdateTime(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int insert = newsMapper.insert(news);
        Assertions.assertNotNull(insert);
        System.out.println(insert);
    }

    /**
     * 测试删除
     */
    @Test
    void testDeleteByPrimaryKey(){
        int i = newsMapper.deleteByPrimaryKey(9L);
        Assertions.assertNotNull(i);
        System.out.println(i);
    }

    /**
     * 测试修改
     */
    @Test
    void testUpdateByPrimaryKey(){
        News news = new News();
        news.setNewsTitle("TestInsert");
        news.setNewsSubUrl("helloBoot");
        int i = newsMapper.updateByPrimaryKey(news);
        System.out.println(i);
    }

    @Test
    public void testSelectAll(){
        List<News> newsList = newsMapper.selectAll();
        Assertions.assertNotNull(newsList);
        Assertions.assertTrue(newsList.size() > 0);
        newsList.forEach((news)->{
            System.out.println(news);
        });
    }

    /**
     * 一个新闻对象 身上有一个属性 是集合 集合中村的是 针对这个新闻的所有评论
     */
    @Test
    public void testSelectById(){
        News news = newsMapper.selectById(6L);
        Assertions.assertNotNull(news);
        System.out.println(news);
        List<NewsComment> commentList = news.getCommentList();
        Assertions.assertNotNull(commentList);
        Assertions.assertTrue(commentList.size() > 0);
        System.out.println(commentList);
    }

    @Test
    public void testSelectByName(){
        News news = new News();
        news.setNewsTitle("hello");
        news.setNewsContent("hello");
        List<News> newsList = newsMapper.selectByName(news);
        Assertions.assertNotNull(newsList);
        Assertions.assertTrue(newsList.size() > 0);
        newsList.forEach((news1)->{
            System.out.println(news1);
        });
    }

    @Test
    public void testMD5(){
        String str = "123456";
        String target = SecureUtil.md5(str);
        System.out.println(target);
    }
}
