package com.geekaca.news.controller.admin;

import com.geekaca.news.domain.News;
import com.geekaca.news.service.NewsService;
import com.geekaca.news.util.NewsConstants;
import com.geekaca.news.util.PageResult;
import com.geekaca.news.util.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//后台管理 文章管理
@Controller
@RequestMapping("/admin")
public class BGNewsController {
    @Autowired
    private NewsService newsService;

    //指向 新闻管理页面
    @GetMapping("blogs")
    public String List(HttpServletRequest req) {
        //为了传递值给前端页面 页面根据他来决定左侧导航菜单高亮 显示哪一个
        req.setAttribute("path","blogs");
        return "admin/blog";
    }


    // 后台 获取新闻列表  ，这个接口是一个标准的返回JSON格式的接口
    @GetMapping("/blogs/list")
    // 结果会被转化为JSON结构
    @ResponseBody
    public Result newsList(@RequestParam("page") Integer page,@RequestParam("limit") Integer limit){
        PageResult pageNewsRs = newsService.getPageNews(page, limit);
        Result result = new Result();
        result.setResultCode(NewsConstants.RESULT_OK);
        result.setData(pageNewsRs);
        return result;
    }

}
