package com.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

//访问界面：http://localhost:8067/sky/swagger-ui.html
@Configuration // 配置到Spring里
@EnableSwagger2 // 开启Swagger2
public class SwaggerConfig {
    // 全部开发者   Docket实例
    @Bean
    public Docket docket(Environment environment) {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("全部开发者")
                .apiInfo(penApiInfo("夕阳全部"))
                // 是否启动   enable(布尔值)
                .enable(penEnvironment(environment))
                // 全局参数说明
                .globalOperationParameters(penParameter())
                // 扫描文件配置    basePackage指定要扫描的包
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.core.controller"))
                // 过滤路径   开发者 .paths(PathSelectors.ant("/xiaoyue/**"))
                .paths(PathSelectors.any())
                .build();
    }
    /*
        多人开发分组
        复制docket下来
        改名docket1
    */
    @Bean
    public Docket docket1(Environment environment) {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("小月开发者")
                .apiInfo(penApiInfo("夕阳小月"))
                // 是否启动   enable(布尔值)
                .enable(penEnvironment(environment))
                // 全局参数说明
                .globalOperationParameters(penParameter())
                // 扫描文件配置    basePackage指定要扫描的包
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.core.controller"))
                // 过滤路径   所有 .paths(PathSelectors.any())
                .paths(PathSelectors.ant("/xiaoyue/**"))
                .build();
    }
    @Bean
    public Docket docket2(Environment environment) {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("彤彤开发者")
                .apiInfo(penApiInfo("夕阳彤彤"))
                // 是否启动   enable(布尔值)
                .enable(penEnvironment(environment))
                // 全局参数说明
                .globalOperationParameters(penParameter())
                // 扫描文件配置    basePackage指定要扫描的包
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.core.controller"))
                // 过滤路径   所有 .paths(PathSelectors.any())
                .paths(PathSelectors.ant("/tongtong/**"))
                .build();
    }
    // 配置Swagger作者信息
    private ApiInfo penApiInfo(String author) {
        Contact contact = new Contact(author, "https://nplayer.js.org/", "122727277@qq.com");
        return new ApiInfo(
                author + "的SwaggerApi文档",
                "远方有你",
                "v1.0",
                "https://vue-json-schema-form.lljj.me/",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }
    private Boolean penEnvironment(Environment environment) {
        // 设置生产环境不可用Swagger
        Profiles profiles = Profiles.of("dev", "test");// 可用数组
        boolean flag = environment.acceptsProfiles(profiles);
        return flag;
    }
    private List<Parameter> penParameter() {
        // 设置全局传参说明
        List<Parameter> pars = new ArrayList<>();
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        parameterBuilder.name("token").description("登录token值").modelRef(new ModelRef("string")).parameterType("header").required(false);
        pars.add(parameterBuilder.build());
        return pars;
    }


}
