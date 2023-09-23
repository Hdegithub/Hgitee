package com.geekaca.news.controller.fore;

import cn.hutool.captcha.ShearCaptcha;
import com.geekaca.news.domain.Link;
import com.geekaca.news.domain.News;
import com.geekaca.news.domain.NewsComment;
import com.geekaca.news.domain.TagNewsCount;
import com.geekaca.news.service.*;
import com.geekaca.news.utils.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 前台
 */
//注解 控制器类
@Controller
@Slf4j
public class NewsController {
    public static String theme = "amaze";
    @Autowired
    private NewsService newsService;
    @Autowired
    private ConfigService configService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private TagService tagService;
    @Autowired
    private LinkService linkService;
    /**
     * 首页
     *
     * @return
     */
    @GetMapping({"/", "/index", "index.html"})
    public String index(HttpServletRequest request) {
        return this.page(request, 1, null);
    }

    @GetMapping({"/page/{pageNum}"})
    private String page(HttpServletRequest request, @PathVariable("pageNum") int pageNum,
                        @RequestParam(name = "keyword", required = false) String keyword) {
        if (keyword != null) {
            return "blog/" + theme + "/index";
        }
        PageResult pageNews = newsService.getPageNews(pageNum, 8, keyword);
        request.setAttribute("blogPageResult", pageNews);
        //最新发布
        request.setAttribute("newBlogs", newsService.getNewsListForIndexPage(1));
        //点击最多
        request.setAttribute("hotBlogs", newsService.getNewsListForIndexPage(0));
        //热门标签
        List<TagNewsCount> tagCounts = tagService.getAll();
        if (tagCounts == null) {
            //创建空集合  JSON []
            tagCounts = Collections.emptyList();
        }
        request.setAttribute("hotTags", tagCounts);
        request.setAttribute("pageName", "首页");
        request.setAttribute("configurations", configService.getAllConfigs());
        return "blog/" + theme + "/index";
    }

    /**
     * 搜索列表页
     *
     * @return
     */
    @GetMapping({"/search/{keyword}"})
    public String search(HttpServletRequest request, @PathVariable("keyword") String keyword) {
        return search(request, 1, keyword);
    }

    /**
     * 搜索列表页
     *
     * @return
     */
    @GetMapping({"/search/{keyword}/{pageNum}"})
    public String search(HttpServletRequest req, @PathVariable("pageNum") int pageNum, @PathVariable("keyword") String keyword) {
        PageResult pageNews = newsService.getPageNews(pageNum, 5, keyword);
        req.setAttribute("blogPageResult", pageNews);
        req.setAttribute("pageName", "搜索");
        req.setAttribute("pageUrl", "search");
        req.setAttribute("keyword", keyword);
        //最新发布
        req.setAttribute("newBlogs", newsService.getNewsListForIndexPage(1));
        //点击最多
        req.setAttribute("hotBlogs", newsService.getNewsListForIndexPage(0));
        //热门标签
        req.setAttribute("hotTags", tagService.getAll());
        req.setAttribute("configurations", configService.getAllConfigs());
        return "blog/" + theme + "/list";
    }

    /**
     * 标签列表页
     *
     * @return
     */
    @GetMapping({"/tag/{tagName}"})
    public String tag(HttpServletRequest request, @PathVariable("tagName") String tagName) {
        return tag(request, tagName, 1);
    }

    /**
     * 标签列表页
     *
     * @return
     */
    @GetMapping({"/tag/{tagName}/{page}"})
    public String tag(HttpServletRequest request, @PathVariable("tagName") String tagName, @PathVariable("page") Integer page) {
        PageResult blogPageResult = newsService.getBlogsPageByTag(tagName, page);
        request.setAttribute("blogPageResult", blogPageResult);
        request.setAttribute("pageName", "标签");
        request.setAttribute("pageUrl", "tag");
        request.setAttribute("keyword", tagName);
        request.setAttribute("newBlogs", newsService.getNewsListForIndexPage(1));
        request.setAttribute("hotBlogs", newsService.getNewsListForIndexPage(0));
        request.setAttribute("hotTags", tagService.selectTagNewsCounts());
        request.setAttribute("configurations", configService.getAllConfigs());
        return "blog/" + theme + "/list";
    }

    //传递集合
    @RequestMapping("/all")
    public String selectAll(HttpServletRequest req) {
        List<News> newsList = newsService.selectAll();
        req.setAttribute("all", newsList);
        return "newsList";
    }

    @RequestMapping("/getByName")
    public List<News> getByName(@RequestBody News news) {
        return newsService.selectByName(news);
    }

    //单个值的传递
    @GetMapping("/toPage")
    public String toPage(HttpServletRequest req) {
        req.setAttribute("name", "Tom");
        //要用@Controller 跳转到名字为hello的页面
        return "hello";
    }

    //从控制器向页面传递对象
    @GetMapping("/newsDetail/{newsId}")
    public String toDetail(HttpServletRequest req, @PathVariable("newsId") Long newsId) {
        News news = newsService.selectById(newsId);
        req.setAttribute("newsInfo", news);
        return "newsDetail";
    }

    /**
     * 详情页
     *
     * @return
     */
    @GetMapping({"/blog/{newsId}", "/article/{newsId}"})
    public String detail(HttpServletRequest request, @PathVariable("newsId") Long newsId, @RequestParam(value = "commentPage", required = false, defaultValue = "1") Integer commentPage) {
        News newsAndComments = newsService.selectById(newsId);
        request.setAttribute("blogDetailVO", newsAndComments);
        request.setAttribute("pageName", "详情");
        request.setAttribute("configurations", configService.getAllConfigs());
        int i = newsService.updateNewsViews(newsId);
        return "blog/" + theme + "/detail";
    }

    /**
     * 评论操作
     */
    @PostMapping(value = "/blog/comment")
    @ResponseBody
    public Result comment(HttpServletRequest request, HttpSession session,
                          @RequestParam Long newsId, @RequestParam String verifyCode,
                          @RequestParam String commentator, @RequestParam String email,
                          @RequestParam String websiteUrl, @RequestParam String commentBody) {
        if (!StringUtils.hasText(verifyCode)) {
            return ResultGenerator.genFailResult("验证码不能为空");
        }
        ShearCaptcha shearCaptcha = (ShearCaptcha) session.getAttribute("verifyCode");
        if (shearCaptcha == null || !shearCaptcha.verify(verifyCode)) {
            return ResultGenerator.genFailResult("验证码错误");
        }
        String ref = request.getHeader("Referer");
        if (!StringUtils.hasText(ref)) {
            return ResultGenerator.genFailResult("非法请求");
        }
        if (null == newsId || newsId < 0) {
            return ResultGenerator.genFailResult("非法请求");
        }
        if (!StringUtils.hasText(commentator)) {
            return ResultGenerator.genFailResult("请输入称呼");
        }
        if (!StringUtils.hasText(email)) {
            return ResultGenerator.genFailResult("请输入邮箱地址");
        }
        if (!PatternUtil.isEmail(email)) {
            return ResultGenerator.genFailResult("请输入正确的邮箱地址");
        }
        if (!StringUtils.hasText(commentBody)) {
            return ResultGenerator.genFailResult("请输入评论内容");
        }
        if (commentBody.trim().length() > 200) {
            return ResultGenerator.genFailResult("评论内容过长");
        }
        NewsComment comment = new NewsComment();
        comment.setNewsId(newsId);
        comment.setCommentator(MyBlogUtils.cleanString(commentator));
        comment.setEmail(email);
        if (PatternUtil.isURL(websiteUrl)) {
            comment.setWebsiteUrl(websiteUrl);
        }
        comment.setCommentBody(MyBlogUtils.cleanString(commentBody));
        return ResultGenerator.genSuccessResult(commentService.addComment(comment));
    }



    /**
     * 友情链接页
     *
     * @return
     */
    @GetMapping({"/link"})
    public String link(HttpServletRequest request) {
        request.setAttribute("pageName", "友情链接");
        Map<Byte, List<Link>> linkMap = linkService.getLinksForLinkPage();
        if (linkMap != null) {
            //判断友链类别并封装数据 0-友链 1-推荐 2-个人网站
            if (linkMap.containsKey((byte) 0)) {
                request.setAttribute("favoriteLinks", linkMap.get((byte) 0));
            }
            if (linkMap.containsKey((byte) 1)) {
                request.setAttribute("recommendLinks", linkMap.get((byte) 1));
            }
            if (linkMap.containsKey((byte) 2)) {
                request.setAttribute("personalLinks", linkMap.get((byte) 2));
            }
        }
        request.setAttribute("configurations", configService.getAllConfigs());
        return "blog/" + theme + "/link";
    }
}
