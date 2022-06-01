package com.core.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// 安全配置-拦截器
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter { // 继承类
    // 用户授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 首页所有人可以访问，功能页对应角色才能访问
        http.authorizeRequests()
                .antMatchers("/").permitAll()  // 首页所有人能访问
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3")
                .and().csrf().disable(); // springboot post 请求报 403错误 ---- 默认是可以自动防御 CSRF 攻击,把它关闭掉
        // 没有权限跳至登录页，需要开启登陆页面
        http.formLogin()
                .loginPage("/toLogin")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/"); //将此处的successForwardUrl使用defaultSuccessUrl替换
        // 注销，开启了注销功能，跳到首页
        http.logout().logoutSuccessUrl("/");
    }

    /*
        用户认证
        密码明文不安全，需要密码编码：PasswordEncoder
    */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 这些数据正常从数据库里读
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("localhost").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1", "vip2", "vip3")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1", "vip2", "vip3");
    }
}
