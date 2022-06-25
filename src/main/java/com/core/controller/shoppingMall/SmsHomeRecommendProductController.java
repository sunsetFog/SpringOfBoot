package com.core.controller.shoppingMall;

import com.core.common.util.ResponseData;
import com.core.common.util.ResponseDataUtil;
import com.core.mapper.shoppingMall.SmsHomeRecommendProductMapper;
import com.core.pojo.shoppingMall.SmsHomeRecommendProduct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "SmsHomeRecommendProductController", description = "首页人气推荐管理")
@RequestMapping("/homeRecommendProduct")
@CrossOrigin
public class SmsHomeRecommendProductController {
    @Autowired
    private SmsHomeRecommendProductMapper smsHomeRecommendProductMapper;

    @ApiOperation("分页查询人气推荐")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseData homeRecommendProductList(@RequestParam(value = "productName", required = false) String productName,
                                                 @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                 @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<SmsHomeRecommendProduct> smsHomeRecommendProducts = smsHomeRecommendProductMapper.selectWay(productName, recommendStatus);
        return ResponseDataUtil.pageStructure(pageNum, pageSize, smsHomeRecommendProducts);
    }
    /*
        传参：
            [
                {
                    "productId": 26,
                    "productName": "哈哥"
                }
            ]
    */
    @ApiOperation("批量添加首页推荐")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseData homeRecommendProductAdd(@RequestBody List<SmsHomeRecommendProduct> homeRecommendProductList) {
        for(SmsHomeRecommendProduct item: homeRecommendProductList) {
            item.setRecommendStatus(1);
            item.setSort(0);
            smsHomeRecommendProductMapper.insertWay(item);
        }
        return ResponseDataUtil.countJudge(homeRecommendProductList.size());
    }
    @ApiOperation("批量修改首页推荐")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseData homeRecommendProductUpdate(@RequestBody List<SmsHomeRecommendProduct> homeRecommendProductList) {
        for(SmsHomeRecommendProduct item: homeRecommendProductList) {
            smsHomeRecommendProductMapper.updateWay(item);
        }
        return ResponseDataUtil.countJudge(homeRecommendProductList.size());
    }
    @ApiOperation("批量删除推荐")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResponseData homeRecommendProductDelete(@RequestParam("ids") List<Long> ids) {
        int count = smsHomeRecommendProductMapper.deleteWay(ids);
        return ResponseDataUtil.countJudge(count);
    }
}
