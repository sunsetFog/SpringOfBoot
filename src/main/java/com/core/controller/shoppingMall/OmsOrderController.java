package com.core.controller.shoppingMall;

import com.core.apiParams.OmsOrderListParam;
import com.core.common.util.ResponseData;
import com.core.common.util.ResponseDataUtil;
import com.core.mapper.shoppingMall.OmsOrderMapper;
import com.core.pojo.shoppingMall.OmsOrder;
import com.core.pojo.shoppingMall.OmsOrderDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "OmsOrderController", description = "订单管理")
@RequestMapping("/order")
public class OmsOrderController {
    @Autowired
    private OmsOrderMapper omsOrderMapper;
    /*
        分页查询,pageSize传9999查所有
        传参：
            {
                "orderSn": "201809150101000001",
                "receiverKeyword": "18033441849",
                "status": 4,
                "orderType": 0,
                "sourceType": 1,
                "createTime": "2018-09-15",
                "pageNum": 1,
                "pageSize": 10
            }
    */
    @ApiOperation("查询订单")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseData orderList(@RequestBody OmsOrderListParam omsOrderListParam) {
        List<OmsOrder> omsOrders = omsOrderMapper.selectWay(omsOrderListParam);
        return ResponseDataUtil.pageStructure(omsOrderListParam.getPageNum(), omsOrderListParam.getPageSize(), omsOrders);
    }
    @ApiOperation("获取订单详情：订单信息、商品信息、操作记录")
    @RequestMapping(value = "details/{id}", method = RequestMethod.GET)
    public ResponseData orderDetails(@PathVariable Long id) {
        OmsOrderDetail omsOrderDetail = omsOrderMapper.selectDetail(id);
        return ResponseDataUtil.buildSuccess(omsOrderDetail);
    }
    @ApiOperation("批量删除订单")
    @RequestMapping(value = "/deleteStatus", method = RequestMethod.GET)
    public ResponseData orderDelete(@RequestParam("ids") List<Long> ids,
                                    @RequestParam(value = "deleteStatus", defaultValue = "1") Integer deleteStatus) {
        for (Long item: ids) {
            omsOrderMapper.update_deleteStatus(item, deleteStatus);
        }
        return ResponseDataUtil.buildSuccess(ids.size());
    }
//    @ApiOperation("批量发货")
//    @RequestMapping(value = "/delivery", method = RequestMethod.POST)
}
