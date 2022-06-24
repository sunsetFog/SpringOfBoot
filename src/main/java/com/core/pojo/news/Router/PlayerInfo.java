package com.core.pojo.news.Router;

import com.core.pojo.shoppingMall.UmsRole;

import java.util.List;

public class PlayerInfo {
    public String username;
    public List<UmsRole> roleList;
    public List<Router> routerList;
    public List<Operation> operationList;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<UmsRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<UmsRole> roleList) {
        this.roleList = roleList;
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
}
