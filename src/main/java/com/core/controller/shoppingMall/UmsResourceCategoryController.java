package com.core.controller.shoppingMall;

import com.core.common.util.ResponseData;
import com.core.common.util.ResponseDataUtil;
import com.core.mapper.shoppingMall.UmsResourceCategoryMapper;
import com.core.pojo.shoppingMall.UmsResourceCategory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "UmsResourceCategoryController", description = "后台资源分类管理")
@RequestMapping("/resourceCategory")
public class UmsResourceCategoryController {
    @Autowired
    private UmsResourceCategoryMapper umsResourceCategoryMapper;

    @ApiOperation("查询所有后台资源分类")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseData resourceCategoryList() {
        List<UmsResourceCategory> umsResourceCategories = umsResourceCategoryMapper.selectWay();
        return ResponseDataUtil.buildSuccess(umsResourceCategories);
    }
}
