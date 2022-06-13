package com.core.controller;

import com.core.common.util.ResponseData;
import com.core.common.util.ResponseDataUtil;
import com.core.pojo.news.LoginParams;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

//@Controller // 走html，接口会报错，因为设置了。。。
@RestController // 不走html
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

    /*
        RestTemplate调用第三方接口
    */
    private final RestTemplate restTemplate;
    public ThymeleafController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    @GetMapping("/toOrder1")
    public ResponseData toOrder1() {
        LoginParams loginParams = new LoginParams();
        loginParams.setUsername("rafael");
        loginParams.setPassword("123456");
        System.out.println("--user000-"+ loginParams);
        ResponseData forObject = restTemplate.getForObject("http://localhost:8062/sky/toApple", ResponseData.class, loginParams);
        return forObject;
    }
    @GetMapping("/toApple")
    public ResponseData toApple(HttpServletRequest request) {
        String user = request.getParameter("user");
        System.out.println("--user111-"+user);
        return ResponseDataUtil.buildSuccess("200", "画好后", user);
    }

    @GetMapping("/toOrder2")
    public ResponseData toOrder2() {
        LoginParams loginParams = new LoginParams();
        loginParams.setUsername("rafael");
        loginParams.setPassword("123456");
        System.out.println("--user000-"+ loginParams);
        ResponseEntity<ResponseData> responseDataResponseEntity = restTemplate.postForEntity("http://localhost:8062/sky/toApple", loginParams, ResponseData.class);
        return responseDataResponseEntity.getBody();
    }
    @PostMapping("/toApple")
    public ResponseData toApple2(@RequestBody LoginParams loginParams) {
        System.out.println("--user222-"+ loginParams);
        return ResponseDataUtil.buildSuccess("200", "画好后", loginParams);
    }
}
