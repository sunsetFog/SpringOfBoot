package com.core.controller.shoppingMall;

import com.core.apiParams.PmsProductQueryParam;
import com.core.common.util.PageResult;
import com.core.common.util.ResponseData;
import com.core.common.util.ResponseDataUtil;
import com.core.mapper.shoppingMall.PmsProductMapper;
import com.core.pojo.shoppingMall.PmsProduct;
import com.core.pojo.shoppingMall.UmsAdmin;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "PmsProductController", description = "商品管理")
@RequestMapping("/product")
public class PmsProductController {
    @Autowired
    private PmsProductMapper pmsProductMapper;
    /*
        分页查询,pageSize传9999查所有
        name字段模糊搜索
        传参：
            {
                "keyword": "华为 HUAWEI P2",
                "productSn": "6946605",
                "productCategoryId": 19,
                "brandId": 3,
                "publishStatus": 1,
                "verifyStatus": 0,
                "pageNum": 1,
                "pageSize": 5
            }
    */
    @ApiOperation("查询商品")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseData productList(@RequestBody PmsProductQueryParam productQueryParam){
        productQueryParam.setKeyword("%" + productQueryParam.getKeyword() + "%");
        List<PmsProduct> pmsProducts = pmsProductMapper.selectWay(productQueryParam);
        // 分页
        PageHelper.startPage(productQueryParam.getPageNum(), productQueryParam.getPageSize());
        PageInfo<PmsProduct> goodsPageInfo = new PageInfo<PmsProduct>(pmsProducts);
        PageResult pageResult = PageResult.getPageResult(goodsPageInfo);
        return ResponseDataUtil.buildSuccess(pageResult);
    }
}
