package com.core.controller.shoppingMall;

import com.core.common.util.PageResult;
import com.core.common.util.ResponseData;
import com.core.common.util.ResponseDataUtil;
import com.core.mapper.shoppingMall.UmsResourceMapper;
import com.core.pojo.shoppingMall.UmsAdmin;
import com.core.pojo.shoppingMall.UmsResource;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Api(tags = "UmsResourceController", description = "后台资源管理")
@RequestMapping("/resource")
public class UmsResourceController {
    @Autowired
    private UmsResourceMapper umsResourceMapper;
    /*
        分页查询,pageSize传9999查所有
        name和url字段模糊搜索
    */
    @ApiOperation("查询所有后台资源")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseData resourceList(@RequestParam(required = false) Long categoryId,
                                     @RequestParam(required = false) String nameKeyword,
                                     @RequestParam(required = false) String urlKeyword,
                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        nameKeyword = '%' + nameKeyword + '%';
        urlKeyword = '%' + urlKeyword + '%';
        List<UmsResource> umsResources = umsResourceMapper.selectWay(categoryId, nameKeyword, urlKeyword);
        // 分页
        return ResponseDataUtil.pageStructure(pageNum, pageSize, umsResources);
    }
    /*
        实际传参：
        {
            "name": "二哈",
            "url": "/ha",
            "categoryId": 1,
            "description": "季节"
        }
    */
    @ApiOperation("添加后台资源")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseData resourceAdd(@RequestBody UmsResource umsResource) {
        umsResource.setCreateTime(new Date());
        int count = umsResourceMapper.insertWay(umsResource);
        return ResponseDataUtil.countJudge(count);
    }
    @ApiOperation("修改后台资源")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseData resourceUpdate(@RequestBody UmsResource umsResource) {
        int count = umsResourceMapper.updateWay(umsResource);
        return ResponseDataUtil.countJudge(count);
    }
    @ApiOperation("根据ID删除后台资源")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ResponseData resourceDelete(@PathVariable Long id) {
        int count = umsResourceMapper.deleteWay(id);
        return ResponseDataUtil.countJudge(count);
    }
}
