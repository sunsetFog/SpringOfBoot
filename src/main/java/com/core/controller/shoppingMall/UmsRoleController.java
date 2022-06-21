package com.core.controller.shoppingMall;

import com.core.common.util.PageResult;
import com.core.common.util.ResponseData;
import com.core.common.util.ResponseDataUtil;
import com.core.mapper.shoppingMall.UmsRoleMapper;
import com.core.pojo.shoppingMall.UmsAdmin;
import com.core.pojo.shoppingMall.UmsRole;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Api(tags = "UmsRoleController", description = "后台用户角色管理")
@RequestMapping("/role")
public class UmsRoleController {
    @Autowired
    private UmsRoleMapper umsRoleMapper;
    /*
        分页查询,pageSize传9999查所有
        username和nick_name字段模糊搜索
    */
    @ApiOperation("根据角色名称分页获取角色列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseData adminList(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        keyword = '%' + keyword + '%';
        // SQL查询
        List<UmsRole> roleList = umsRoleMapper.selectWay(keyword);
        System.out.println("--roleList--"+roleList);
        // 分页
        return ResponseDataUtil.pageStructure(pageNum, pageSize, roleList);
    }
    /*
        添加角色
        实际传参：
            {
                "name": "二哈将军",
                "description": "熬~",
                "status": 1
            }
    */
    @ApiOperation("添加角色")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseData roleAdd(@RequestBody UmsRole umsRole) {
        umsRole.setCreateTime(new Date());
        umsRole.setAdminCount(0);
        umsRole.setSort(0);
        System.out.println("--umsRole--"+umsRole);
        int count = umsRoleMapper.insertWay(umsRole);
        return ResponseDataUtil.countJudge(count);
    }
    @ApiOperation("修改角色")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseData roleUpdate(@RequestBody UmsRole umsRole) {
        int count = umsRoleMapper.updateWay(umsRole);
        return ResponseDataUtil.countJudge(count);
    }
    @ApiOperation("删除角色")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ResponseData adminDelete(@PathVariable Long id) {
        int count = umsRoleMapper.deleteWay(id);
        return ResponseDataUtil.countJudge(count);
    }
}
