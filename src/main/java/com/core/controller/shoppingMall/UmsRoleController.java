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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<UmsRole> goodsPageInfo = new PageInfo<UmsRole>(roleList);
        PageResult pageResult = PageResult.getPageResult(goodsPageInfo);
        return ResponseDataUtil.buildSuccess(pageResult);
    }
}
