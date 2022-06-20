package com.core.controller.shoppingMall;

import com.core.apiParams.PmsProductCategoryAddParam;
import com.core.common.util.PageResult;
import com.core.common.util.ResponseData;
import com.core.common.util.ResponseDataUtil;
import com.core.mapper.shoppingMall.PmsProductCategoryAttributeRelationMapper;
import com.core.mapper.shoppingMall.PmsProductCategoryMapper;
import com.core.pojo.shoppingMall.*;
import com.core.pojo.shoppingMall.PmsProductCategory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "PmsProductCategoryController", description = "商品分类管理")
@RequestMapping("/productCategory")
public class PmsProductCategoryController {
    @Autowired
    private PmsProductCategoryMapper pmsProductCategoryMapper;
    @Autowired
    private PmsProductCategoryAttributeRelationMapper pmsProductCategoryAttributeRelationMapper;

    @ApiOperation("商品分类树形结构")
    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    public ResponseData productCategoryTree() {
        List<PmsProductCategory> menuList = pmsProductCategoryMapper.selectWay(null);

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
    @ApiOperation("分页查询商品分类")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseData productCategoryList(@RequestParam(value = "parentId") Long parentId,
                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        List<PmsProductCategory> pmsProductCategories = pmsProductCategoryMapper.selectWay(parentId);
        // 分页
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<PmsProductCategory> goodsPageInfo = new PageInfo<PmsProductCategory>(pmsProductCategories);
        PageResult pageResult = PageResult.getPageResult(goodsPageInfo);
        return ResponseDataUtil.buildSuccess(pageResult);
    }
    /*
        数据校验
    */
    @ApiOperation("添加商品分类")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseData productCategoryAdd(@Validated @RequestBody PmsProductCategoryAddParam pmsProductCategoryAddParam) {
        PmsProductCategory pmsProductCategory = new PmsProductCategory();
        pmsProductCategory.setProductCount(0);
        // 类复制属性值
        BeanUtils.copyProperties(pmsProductCategoryAddParam, pmsProductCategory);
        // 设置level
        setCategoryLevel(pmsProductCategory);
        // SQL插入
        int count = pmsProductCategoryMapper.insertWay(pmsProductCategory);
        // SQL插入筛选属性
        List<Long> productAttributeIdList = pmsProductCategoryAddParam.getProductAttributeIdList();
        if(!CollectionUtils.isEmpty(productAttributeIdList)){// 判断集合是否为空
            insertRelationList(pmsProductCategory.getId(), productAttributeIdList);
        }
        return ResponseDataUtil.buildSuccess(count);
    }
    /**
     * 根据分类的parentId设置分类的level
     */
    private void setCategoryLevel(PmsProductCategory productCategory) {
        // 没有父分类时为一级分类
        productCategory.setLevel(0);
        if (productCategory.getParentId() == 0) return;
        // 有父分类时,SQL查询父类的level设置
        PmsProductCategory pmsProductCategory = pmsProductCategoryMapper.selectIdWay(productCategory.getParentId());
        productCategory.setLevel(pmsProductCategory.getLevel() + 1);
        System.out.println("---level---"+productCategory.getLevel());
    }
    /**
     * 批量插入商品分类与筛选属性关系表
     * @param productCategoryId 商品分类id
     * @param productAttributeIdList 相关商品筛选属性id集合
     */
    private void insertRelationList(Long productCategoryId, List<Long> productAttributeIdList) {
        List<PmsProductCategoryAttributeRelation> relationList = new ArrayList<>();
        for (Long productAttrId : productAttributeIdList) {
            PmsProductCategoryAttributeRelation relation = new PmsProductCategoryAttributeRelation();
            relation.setProductAttributeId(productAttrId);
            relation.setProductCategoryId(productCategoryId);
            relationList.add(relation);
        }
        pmsProductCategoryAttributeRelationMapper.insertList(relationList);
    }
}
