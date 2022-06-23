package com.core.controller.shoppingMall;

import com.core.common.util.ResponseData;
import com.core.common.util.ResponseDataUtil;
import com.core.mapper.shoppingMall.SmsHomeBrandMapper;
import com.core.pojo.shoppingMall.SmsHomeBrand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "SmsHomeBrandController", description = "品牌推荐管理")
@RequestMapping("/homeBrand")
public class SmsHomeBrandController {
    @Autowired
    private SmsHomeBrandMapper smsHomeBrandMapper;

    @ApiOperation("分页查询推荐品牌")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseData homeBrandList(@RequestParam(value = "brandName", required = false) String brandName,
                                      @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        List<SmsHomeBrand> smsHomeBrands = smsHomeBrandMapper.selectWay(brandName, recommendStatus);
        return ResponseDataUtil.pageStructure(pageNum, pageSize, smsHomeBrands);
    }
    /*
        传参:
            [
                {
                    "brandId": 6,
                    "brandName": "哈哥"
                }
            ]
    */
    @ApiOperation("添加首页推荐品牌")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseData homeBrandAdd(@RequestBody List<SmsHomeBrand> homeBrandList) {
        for(SmsHomeBrand item: homeBrandList) {
            item.setRecommendStatus(1);
            item.setSort(0);
            smsHomeBrandMapper.insertWay(item);
        }
        return ResponseDataUtil.countJudge(homeBrandList.size());
    }
    @ApiOperation("修改首页推荐品牌")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseData homeBrandUpdate(@RequestBody SmsHomeBrand homeBrandList) {
        int count = smsHomeBrandMapper.updateWay(homeBrandList);
        return ResponseDataUtil.countJudge(count);
    }
    @ApiOperation("批量删除推荐品牌")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResponseData homeBrandDelete(@RequestParam("ids") List<Long> ids) {
        int count = smsHomeBrandMapper.deleteWay(ids);
        return ResponseDataUtil.countJudge(count);
    }
}
