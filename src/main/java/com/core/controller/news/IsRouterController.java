package com.core.controller.news;

import com.core.mapper.news.RouterMapper;
import com.core.pojo.news.Router.Operation;
import com.core.pojo.news.Router.Router;
import com.core.common.util.ResponseData;
import com.core.common.util.ResponseDataUtil;
import com.core.pojo.news.Router.RouterIndex;
import com.core.service.RedisUtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
public class IsRouterController {
    @Autowired
    private RouterMapper routerMapper;
    @Resource
    private RedisUtilsService redisUtilsService;
    @GetMapping("/newsRouterList")
    public ResponseData luyouWay(HttpServletRequest request) {
        // 获取session缓存
        String username = redisUtilsService.getCacheStringInfo("username");
        System.out.println("---user--88--"+username);
//        System.out.println("---user--99--"+routerMapper.isRouterList(username));
        List<Router> routerList = routerMapper.isRouterList(username);
        List<Operation> operationList = routerMapper.isOperationList(username);
        RouterIndex routerIndex = new RouterIndex();
        routerIndex.routerList = routerList;
        routerIndex.operationList = operationList;
        return ResponseDataUtil.buildSuccess(routerIndex);
    }
}
