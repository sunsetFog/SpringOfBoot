package com.core.controller.news;

import com.core.mapper.news.RouterMapper;
import com.core.mapper.shoppingMall.UmsAdminMapper;
import com.core.mapper.shoppingMall.UmsAdminRoleRelationMapper;
import com.core.pojo.news.Router.Operation;
import com.core.pojo.news.Router.Router;
import com.core.common.util.ResponseData;
import com.core.common.util.ResponseDataUtil;
import com.core.pojo.news.Router.PlayerInfo;
import com.core.pojo.shoppingMall.UmsAdmin;
import com.core.pojo.shoppingMall.UmsRole;
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
    @Autowired
    private UmsAdminMapper umsAdminMapper;
    @Autowired
    private UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;
    @Resource
    private RedisUtilsService redisUtilsService;
    @GetMapping("/newsRouterList")
    public ResponseData luyouWay(HttpServletRequest request) {
        // 获取session缓存
        String username = redisUtilsService.getCacheStringInfo("username");

        List<UmsAdmin> adminList = umsAdminMapper.selectWay(username);
        UmsAdmin umsAdmin = adminList.get(0);
        List<Router> routerList = routerMapper.isRouterList(username);
        List<Operation> operationList = routerMapper.isOperationList(username);
        List<UmsRole> roleList = umsAdminRoleRelationMapper.selectWay(umsAdmin.getId());

        PlayerInfo playerInfo = new PlayerInfo();
        playerInfo.setUsername(username);
        playerInfo.setRoleList(roleList);
        playerInfo.setRouterList(routerList);
        playerInfo.setOperationList(operationList);
        return ResponseDataUtil.buildSuccess(playerInfo);
    }
}
