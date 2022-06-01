package com.core.config;

import com.core.service.RedisUtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration // 配置
public class MyMvcConfig implements WebMvcConfigurer {
    // 路由视图
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/viewWater").setViewName("water");
        registry.addViewController("/yuangong").setViewName("/employee/list.html");
    }
    // 注册自定义国际化组件
    @Bean
    public LocaleResolver localeResolver() {
        return new Lang8n();
    }
    // 写入redis
    @Autowired
    private RedisUtilsService redisUtilsService;
    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*
            addPathPatterns使http拦截器触发：所有
            excludePathPatterns使http拦截器不会触发：一个个加，登录接口不需要拦截
        */
//        registry.addInterceptor(new HttpInterceptor(redisUtilsService)) // 实例化http拦截器，顺便带参构造
//                .addPathPatterns("/**")
//                .excludePathPatterns("/","index.html","/user/login", "/shop/list","/css/**","/js/**","/img/**");
    }
    // 上传文件或图片 ----- 把逻辑路径自动映射为物理路径
    @Value("${file.uploadFolder}")// 读取配置文件信息
    private String uploadFolder;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String fileUpload = "file:" + System.getProperty("user.dir") + uploadFolder;
        System.out.println("--fileUpload--"+fileUpload);
//        registry.addResourceHandler(staticAccessPath).addResourceLocations(fileUpload);
        registry.addResourceHandler("/img/avatorImages/**").addResourceLocations(fileUpload);
    }
}
