package com.core.pojo.news.Router;

import com.core.pojo.shoppingMall.UmsMenu;
import com.core.pojo.shoppingMall.UmsRole;

import java.util.List;

public class PlayerInfo {
    private String username;
    private List<Router> routerList;
    private List<Operation> operationList;
    private List<UmsRole> roleList;
    private List<UmsMenu> menuList;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Router> getRouterList() {
        return routerList;
    }

    public void setRouterList(List<Router> routerList) {
        this.routerList = routerList;
    }

    public List<Operation> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<Operation> operationList) {
        this.operationList = operationList;
    }

    public List<UmsRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<UmsRole> roleList) {
        this.roleList = roleList;
    }

    public List<UmsMenu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<UmsMenu> menuList) {
        this.menuList = menuList;
    }
}
