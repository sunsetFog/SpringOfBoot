package com.core.config;

import com.core.service.RedisUtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

/*
   装配扩展SpringMvc配置
   SpringMVC官方文档：https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/boot-features-developing-web-applications.html
*/
@Configuration // 配置
public class SpringMvcConfig implements WebMvcConfigurer {
    // 配置路由视图  访问http://localhost:8062/sky/yuangong
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
    // 注册http拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*
            addPathPatterns拦截
            excludePathPatterns不拦截
        */
//        registry.addInterceptor(new HttpInterceptor(redisUtilsService)) // 实例化http拦截器，顺便带参构造
//                .addPathPatterns("/**")// 拦截所有
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
    /*
        这里是全局跨域设置
        局部跨域：@CrossOrigin
    */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/sky/*")// 映射服务器接口，使跨域访问
            // .allowedOrigins("http://localhost:8062")// 域名来源
            .allowedMethods("GET", "POST", "DELETE", "PUT");// 允许请求方法
    }
}
