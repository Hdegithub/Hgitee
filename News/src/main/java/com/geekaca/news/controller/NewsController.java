package com.geekaca.news.controller;

import com.geekaca.news.domain.News;
import com.geekaca.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//注解 控制器类
@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @RequestMapping("/all")
    public Result selectAll(){
        List<News> newsList = newsService.selectAll();
        Integer code = (newsList ==  null  ? Code. GET_ERR: Code.GET_OK);
        String msg = (newsList ==  null  ? "查询失败" : "");
        Result result = new Result(code,msg,newsList);
        return result;
    }
}
