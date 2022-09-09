package com.core.config;

import com.core.mapper.news.UserMapper;
import com.core.pojo.news.LoginParams;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/*
Shiro自定义UserRealm
1.登录，触发认证方法
2.接着跳要授权的/user/add，触发授权方法
*/
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
        System.out.println("--Shiro--授权--");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("user:update");
        // 拿到User对象，认证return要传user进来
        Subject subject = SecurityUtils.getSubject();
        LoginParams currentLoginParams = (LoginParams) subject.getPrincipal();
        System.out.println("--currentUser--"+ currentLoginParams);
        //添加角色
        info.addRole("user");
        // 设置当前用户的权限
//        info.addStringPermission(currentUser.getPerms());
        return info;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("--Shiro--认证--");
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        /*
            连接真实的数据库
            获取用户名：userToken.getUsername()  关联user实体类的
            获取密码：userToken.getPassword()  java: 不兼容的类型: char[]无法转换为java.lang.String
            https://www.cnblogs.com/yongh/p/9689798.html
        */
        System.out.println("--userToken.getUsername()--"+userToken.getUsername());
        System.out.println("--userToken.getPassword()--"+userToken.getPassword());
        // char[] 类型转String类型
        String password = String.valueOf(userToken.getPassword());
        System.out.println("--password--"+password);
        LoginParams loginParams = userMapper.loginVerify(userToken.getUsername(), password);
        System.out.println("--res-006-"+ loginParams);


        if(loginParams == null) {
            return null;// 抛出异常
        }

        // 模拟假数据，用户名，密码   真实数据库中取
//        String name = "root";
//        String password = "123456";

//        if(!userToken.getUsername().equals(name)) {
//            return null;// 抛出异常
//        }
        // 密码认证，shiro做~
        return new SimpleAuthenticationInfo(loginParams, password, "");
    }
}
