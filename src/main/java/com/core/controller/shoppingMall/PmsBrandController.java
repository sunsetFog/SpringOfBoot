package com.core.controller.shoppingMall;

import com.core.apiParams.PmsBrandAddParam;
import com.core.common.util.PageResult;
import com.core.common.util.ResponseData;
import com.core.common.util.ResponseDataUtil;
import com.core.mapper.shoppingMall.PmsBrandMapper;
import com.core.pojo.shoppingMall.PmsBrand;
import com.core.pojo.shoppingMall.PmsProduct;
import com.core.pojo.shoppingMall.UmsAdmin;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "PmsBrandController", description = "商品品牌管理")
@RequestMapping("/brand")
public class PmsBrandController {
    @Autowired
    private PmsBrandMapper pmsBrandMapper;

    /*
        分页查询,pageSize传9999查所有
        name字段模糊搜索
    */
    @ApiOperation(value = "根据品牌名称分页获取品牌列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseData brandList(@RequestParam(value = "keyword", required = false) String keyword,
                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        keyword = '%' + keyword + '%';
        List<PmsBrand> pmsBrands = pmsBrandMapper.selectWay(keyword);
        // 分页
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<PmsBrand> goodsPageInfo = new PageInfo<PmsBrand>(pmsBrands);
        PageResult pageResult = PageResult.getPageResult(goodsPageInfo);
        return ResponseDataUtil.buildSuccess(pageResult);
    }
    @ApiOperation(value = "添加品牌")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseData brandAdd(@RequestBody PmsBrandAddParam pmsBrandAddParam) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(pmsBrandAddParam, pmsBrand);
        //如果创建时首字母为空，取名称的第一个为首字母
        if (StringUtils.isEmpty(pmsBrand.getFirstLetter())) {
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0, 1));
        }
        int count = pmsBrandMapper.insertWay(pmsBrand);
        if (count > 0) {
            return ResponseDataUtil.buildSuccess(count);
        } else {
            return ResponseDataUtil.buildError();
        }
    }
    @ApiOperation(value = "更新品牌")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseData brandUpdate(@RequestBody PmsBrandAddParam pmsBrandAddParam) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(pmsBrandAddParam, pmsBrand);
        //如果创建时首字母为空，取名称的第一个为首字母
        if (StringUtils.isEmpty(pmsBrand.getFirstLetter())) {
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0, 1));
        }
        int count = pmsBrandMapper.updateWay(pmsBrand);
        //更新品牌时要更新商品中的品牌名称
        PmsProduct product = new PmsProduct();
        product.setBrandName(pmsBrand.getName());
        return ResponseDataUtil.buildSuccess(count);
    }
}
