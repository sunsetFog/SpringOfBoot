package com.core.controller.shoppingMall;

import com.core.common.util.ResponseData;
import com.core.common.util.ResponseDataUtil;
import com.core.mapper.shoppingMall.UmsAdminRoleRelationMapper;
import com.core.pojo.shoppingMall.UmsAdminRoleRelation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "UmsAdminRoleRelationController", description = "用户与角色表")
@RequestMapping("/admin_role")
public class UmsAdminRoleRelationController {
    @Autowired
    private UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;
    /*
        传参：
            {
                "adminId": 1,
                "roleIds": "2,5"
            }
    */
    @ApiOperation("给用户分配角色")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ResponseData adminRoleUpdate(@RequestParam("adminId") Long adminId,
                                        @RequestParam("roleIds") List<Long> roleIds) {
        // 删除adminId的数据
        umsAdminRoleRelationMapper.deleteWay(adminId);
        // 批量新增：一个用户多个角色
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<UmsAdminRoleRelation> list = new ArrayList<>();
            for (Long roleId : roleIds) {
                UmsAdminRoleRelation roleRelation = new UmsAdminRoleRelation();
                roleRelation.setAdminId(adminId);
                roleRelation.setRoleId(roleId);
                list.add(roleRelation);
            }
            umsAdminRoleRelationMapper.insertList(list);
        }

        int count = roleIds == null ? 0 : roleIds.size();
        if (count > 0) {
            return ResponseDataUtil.buildSuccess(count);
        } else {
            return ResponseDataUtil.buildError();
        }
    }
}
