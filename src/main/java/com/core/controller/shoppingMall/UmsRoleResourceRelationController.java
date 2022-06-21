package com.core.controller.shoppingMall;

import com.core.common.util.ResponseData;
import com.core.common.util.ResponseDataUtil;
import com.core.mapper.shoppingMall.UmsRoleResourceRelationMapper;
import com.core.pojo.shoppingMall.UmsRoleResourceRelation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "UmsRoleResourceRelationController", description = "角色与资源")
@RequestMapping("/role_resource")
public class UmsRoleResourceRelationController {
    @Autowired
    private UmsRoleResourceRelationMapper umsRoleResourceRelationMapper;
    /*
        传参：
            {
                "roleId": 1,
                "resourceIds": "1,2,3,4,5,6,23,24"
            }
    */
    @ApiOperation("给角色分配资源")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ResponseData roleResourceUpdate(@RequestParam Long roleId, @RequestParam List<Long> resourceIds) {
        // 删除roleId的数据
        umsRoleResourceRelationMapper.deleteWay(roleId);
        // 批量新增：一个角色多个菜单
        for (Long resourceId : resourceIds) {
            UmsRoleResourceRelation umsRoleResourceRelation = new UmsRoleResourceRelation();
            umsRoleResourceRelation.setRoleId(roleId);
            umsRoleResourceRelation.setResourceId(resourceId);
            umsRoleResourceRelationMapper.insertList(umsRoleResourceRelation);
        }

        int count = resourceIds == null ? 0 : resourceIds.size();
        return ResponseDataUtil.countJudge(count);
    }
}
