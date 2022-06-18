package com.core.controller.shoppingMall;

import com.core.common.util.ResponseData;
import com.core.common.util.ResponseDataUtil;
import com.core.mapper.shoppingMall.PmsProductCategoryMapper;
import com.core.pojo.shoppingMall.PmsProductCategory;
import com.core.pojo.shoppingMall.PmsProductCategory;
import com.core.pojo.shoppingMall.PmsProductCategoryNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "PmsProductCategoryController", description = "商品分类管理")
@RequestMapping("/productCategory")
public class PmsProductCategoryController {
    @Autowired
    private PmsProductCategoryMapper pmsProductCategoryMapper;

    @ApiOperation("商品分类树形结构")
    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    public ResponseData productCategoryTree() {
        List<PmsProductCategory> menuList = pmsProductCategoryMapper.selectWay();

        List<PmsProductCategoryNode> result = menuList.stream()
                .filter(menu -> menu.getParentId().equals(0L))
                .map(menu -> covertMenuNode(menu, menuList)).collect(Collectors.toList());
        return ResponseDataUtil.buildSuccess(result);
    }
    /**
     * 将PmsProductCategory转化为PmsProductCategoryNode并设置children属性
     */
    private PmsProductCategoryNode covertMenuNode(PmsProductCategory menu, List<PmsProductCategory> menuList) {
        PmsProductCategoryNode node = new PmsProductCategoryNode();
        BeanUtils.copyProperties(menu, node);
        List<PmsProductCategoryNode> children = menuList.stream()
                .filter(subMenu -> subMenu.getParentId().equals(menu.getId()))
                .map(subMenu -> covertMenuNode(subMenu, menuList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
