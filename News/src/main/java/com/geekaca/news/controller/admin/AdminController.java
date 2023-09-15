package com.geekaca.news.controller.admin;

import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.crypto.SecureUtil;
import com.geekaca.news.domain.AdminUser;
import com.geekaca.news.mapper.AdminUserMapper;
import com.geekaca.news.service.AdminUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminUserService userService;
    //接收登录

    @GetMapping("/login")
    public String login(){
        return "/admin/login";
    }

    @RequestMapping("/doLogin")
    public String doLogin(@RequestParam("userName") String userName,
                          @RequestParam("password") String password,
                          @RequestParam("verifyCode") String verifyCode,
                          HttpSession session) {
        if (!StringUtils.hasText(userName) || !StringUtils.hasText(password)) {
            session.setAttribute("errorMsg", "用户名或密码不能为空");
            return "admin/login";
        }
        //判断用户输入的验证码 和之前生成的验证码是否一致 （用户打开登录页面，访问了验证码控制器，把验证码存入了session）
        //会话：每个用户各自独立的 相互隔离的
        ShearCaptcha shearCaptcha = (ShearCaptcha) session.getAttribute("verifyCode");
        if (shearCaptcha == null || !shearCaptcha.verify(verifyCode)) {
            session.setAttribute("errorMsg", "验证码错误");
            return "admin/login";
        }
        //连接DB 验证用户名和密码
        AdminUser user = userService.login(userName, SecureUtil.md5(password));
        if (user == null){
            session.setAttribute("errorMsg", "登陆失败");
            return "admin/login";
        } else {
            session.setAttribute("loginUser", user.getNickName());
            session.setAttribute("loginUserId", user.getAdminUserId());
            //session过期时间设置为7200秒 即两小时
            //session.setMaxInactiveInterval(60 * 60 * 2);
            return "redirect:/admin/index";
        }
    }

    //登录成功后访问的地址
    @GetMapping({"","/index","/index.html"})
    public String index(HttpServletRequest req){
        req.setAttribute("path","index");
        //类别数量 ctrl + Alt 鼠标左键点击，跳转函数实现
        req.setAttribute("categoryCount",0);
        req.setAttribute("blogCount",0);
        req.setAttribute("linkCount",0);
        req.setAttribute("tagCount",0);
        req.setAttribute("commentCount",0);
        return "admin/index";
    }
}

