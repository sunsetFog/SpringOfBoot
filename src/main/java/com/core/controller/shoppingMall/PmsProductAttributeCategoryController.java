package com.core.controller.shoppingMall;

import com.core.common.util.PageResult;
import com.core.common.util.ResponseData;
import com.core.common.util.ResponseDataUtil;
import com.core.mapper.shoppingMall.PmsProductAttributeCategoryMapper;
import com.core.pojo.shoppingMall.PmsBrand;
import com.core.pojo.shoppingMall.PmsProductAttributeCategory;
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
@Api(tags = "PmsProductAttributeCategoryController", description = "商品属性分类管理")
@RequestMapping("/PmsProductAttributeCategory")
public class PmsProductAttributeCategoryController {
    @Autowired
    private PmsProductAttributeCategoryMapper pmsProductAttributeCategoryMapper;
    /*
        分页查询,pageSize传9999查所有
    */
    @ApiOperation("分页获取所有商品属性分类")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseData PmsProductAttributeCategoryList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<PmsProductAttributeCategory> pmsProductAttributeCategories = pmsProductAttributeCategoryMapper.selectWay();
        // 分页
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<PmsProductAttributeCategory> goodsPageInfo = new PageInfo<PmsProductAttributeCategory>(pmsProductAttributeCategories);
        PageResult pageResult = PageResult.getPageResult(goodsPageInfo);
        return ResponseDataUtil.buildSuccess(pageResult);
    }
}
