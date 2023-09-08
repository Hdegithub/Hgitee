package com.geekaca.news;

import com.geekaca.news.domain.News;
import com.geekaca.news.mapper.NewsMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NewsApplicationTests {

    @Autowired
    private NewsMapper newsMapper;

    @Test
    void testSelectByPrimaryKey() {
        News news = newsMapper.selectByPrimaryKey(5L);
        Assertions.assertNotNull(news);
        System.out.println(news);
    }

}
