package com.core.controller.shoppingMall;

import com.core.common.util.ResponseData;
import com.core.common.util.ResponseDataUtil;
import com.core.mapper.shoppingMall.SmsCouponHistoryMapper;
import com.core.pojo.shoppingMall.SmsCouponHistory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "SmsCouponHistoryController", description = "优惠券领取记录管理")
@RequestMapping("/couponHistory")
public class SmsCouponHistoryController {
    @Autowired
    private SmsCouponHistoryMapper smsCouponHistoryMapper;
    /*
        传参:
            {
                "couponId": 2,
                "useStatus": 1,
                "orderSn": "201809150101000001",
                "pageNum": 1,
                "pageSize": 10
            }
    */
    @ApiOperation("根据优惠券id，使用状态，订单编号分页获取领取记录")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseData couponHistoryList(@RequestParam(value = "couponId", required = false) Long couponId,
                                          @RequestParam(value = "useStatus", required = false) Integer useStatus,
                                          @RequestParam(value = "orderSn", required = false) String orderSn,
                                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        List<SmsCouponHistory> smsCouponHistories = smsCouponHistoryMapper.selectWay(couponId, useStatus, orderSn);
        return ResponseDataUtil.pageStructure(pageNum, pageSize, smsCouponHistories);
    }
}
