package com.core.controller.shoppingMall;

import com.core.common.util.ResponseData;
import com.core.common.util.ResponseDataUtil;
import com.core.mapper.shoppingMall.UmsRoleMenuRelationMapper;
import com.core.pojo.shoppingMall.UmsRoleMenuRelation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "UmsRoleMenuRelationController", description = "角色与菜单")
@RequestMapping("/role_menu")
public class UmsRoleMenuRelationController {
    @Autowired
    private UmsRoleMenuRelationMapper umsRoleMenuRelationMapper;

    @ApiOperation("给角色分配菜单")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ResponseData roleMenuUpdate(@RequestParam Long roleId, @RequestParam List<Long> menuIds) {
        // 删除roleId的数据
        umsRoleMenuRelationMapper.deleteWay(roleId);
        // 批量新增：一个角色多个菜单
        for (Long menuId : menuIds) {
            UmsRoleMenuRelation relation = new UmsRoleMenuRelation();
            relation.setRoleId(roleId);
            relation.setMenuId(menuId);
            umsRoleMenuRelationMapper.insertList(relation);
        }

        int count = menuIds == null ? 0 : menuIds.size();
        if (count > 0) {
            return ResponseDataUtil.buildSuccess(count);
        } else {
            return ResponseDataUtil.buildError();
        }
    }
}
