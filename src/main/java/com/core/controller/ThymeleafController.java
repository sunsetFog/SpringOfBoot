package com.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.Arrays;

@Controller // 走html
//@RestController // 不走html
public class ThymeleafController {
    // 简单接口：http://localhost:8080/hello
    @GetMapping("/hello")
    // 调用业务，接收前端参数
    public String hello() {
        return "Hello World!!!";
    }

    /*
        路由视图的两种方式：
        1.MyMvcConfig的路由视图
        2.@RequestMapping({"/", "/water.html"})
    */
    @GetMapping({"/", "/water.html"})
    public String water(Model model) {
        model.addAttribute("msg", "model信息<span>识别标签</span>");
        model.addAttribute("users", Arrays.asList("红红", "晴晴"));
        return "water";// 跳转的页面    二层写法：employee/list
    }

    @GetMapping("/user/isLogin")
    public String toBeLogin(Model model) {
        model.addAttribute("message", "请输入用户名与密码");
        return "/user/login";
    }
    @GetMapping("/user/add")
    public String toBeAdd() {
        return "/user/add";
    }
    @GetMapping("/user/update")
    public String toBeUpdate() {
        return "/user/update";
    }
    @GetMapping("/user/noauth")
    public String toBeNoauth() {
        return "/user/noauth";
    }
}
