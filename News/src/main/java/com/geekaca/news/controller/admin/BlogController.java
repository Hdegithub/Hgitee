package com.geekaca.news.controller.admin;

import com.geekaca.news.domain.News;
import com.geekaca.news.service.NewsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

//后台管理 文章管理
@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private NewsService newsService;
    // 后台 获取新闻列表  ，这个接口是一个标准的返回JSON格式的接口
    @GetMapping("/blogs/list")
    public String findNewsList(HttpServletRequest req){
        List<News> newsList = newsService.findNewsList();
        req.setAttribute("all",newsList);
        return "newsList";
    }

}
