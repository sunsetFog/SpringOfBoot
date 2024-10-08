package com.stars.controller.news;

import com.stars.common.redis.JWTUtils;
import com.stars.common.redis.RedisKeySplicing;
import com.stars.mapper.news.UserMapper;
import com.stars.common.util.ResponseData;
import com.stars.common.util.ResponseDataUtil;
import com.stars.pojo.news.LoginParams;
import com.stars.service.RedisUtilsService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.UUID;

/*
自定义的UserRealm
*/
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Resource
    private RedisUtilsService redisUtilsService;
    @ResponseBody
    @PostMapping("/user/login")
    public ResponseData userLogin(HttpServletRequest req, HttpSession session) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String code = new String();
        String msg = new String();
        session.setAttribute("username", username);// 缓存
        System.out.println("--username--"+username);
        System.out.println("--password--"+password);

        // 获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        // 封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        System.out.println("--subject--"+subject);
        System.out.println("--token--"+token);

        try {
            subject.login(token);
            code = "200";
            msg = "登录成功";
        } catch (UnknownAccountException e) {
            code = "0";
            msg = "用户名错误";
        } catch (IncorrectCredentialsException e) {
            code = "0";
            msg = "密码错误";
        } catch (AuthorizationException e) {
            code = "0";
            msg = "没有权限";
        }
        // 拿到User对象，认证return要传user进来
        LoginParams currentLoginParams = (LoginParams) subject.getPrincipal();
        System.out.println("--currentUser--"+ currentLoginParams);
        // 用户密码错误
        if(currentLoginParams == null) {
            return ResponseDataUtil.buildSuccess(code, msg);
        }

        String isToken = null;
        try {
            isToken = JWTUtils.generTokenByRS256(currentLoginParams);

            LoginParams beLoginParams = JWTUtils.verifierTokenBySysUser(isToken);
            System.out.println("--token校验--"+ beLoginParams);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String randomToken = UUID.randomUUID().toString();

        HashMap<String, String> Sites = new HashMap<>();
        Sites.put("token", isToken);

        System.out.println("--isToken--"+isToken);
        System.out.println("--随机token--"+randomToken);


        // redis存储token
        String appleKey = RedisKeySplicing.getUserToken(currentLoginParams.getUsername());
        System.out.println("--appleKey--"+appleKey);
        redisUtilsService.cacheStringInfoByDay(appleKey, isToken, 1);
        redisUtilsService.cacheStringInfoByDay("username", username, 1);
        String cacheStringInfo = redisUtilsService.getCacheStringInfo(appleKey);
        System.out.println("--redis存储token的值--"+cacheStringInfo);


//        int res = userMapper.loginVerify(username, password);
//        System.out.println("--res--"+res);
//        if(res == 1) {
//            code = "200";
//            msg = "登录成功";
//        } else if(res == 0) {
//            code = "0";
//            msg = "用户名或密码错误";
//        }
        return ResponseDataUtil.buildSuccess(code, msg, Sites);
    }
}
