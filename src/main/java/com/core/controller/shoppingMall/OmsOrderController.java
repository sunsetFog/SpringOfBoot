package com.core.controller.shoppingMall;

import com.core.apiParams.OmsOrderDeliveryParam;
import com.core.apiParams.OmsOrderListParam;
import com.core.common.util.ResponseData;
import com.core.common.util.ResponseDataUtil;
import com.core.mapper.shoppingMall.OmsOrderMapper;
import com.core.mapper.shoppingMall.OmsOrderOperateHistoryMapper;
import com.core.pojo.shoppingMall.OmsOrder;
import com.core.pojo.shoppingMall.OmsOrderDetail;
import com.core.pojo.shoppingMall.OmsOrderOperateHistory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "OmsOrderController", description = "订单管理")
@RequestMapping("/order")
public class OmsOrderController {
    @Autowired
    private OmsOrderMapper omsOrderMapper;
    @Autowired
    private OmsOrderOperateHistoryMapper omsOrderOperateHistoryMapper;
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
    /*
        传参：
        [
            {
                "orderId": 13,
                "deliveryCompany": "顺丰快递",
                "deliverySn": "123"
            }
        ]
    */
    @ApiOperation("批量发货")
    @RequestMapping(value = "/update/delivery", method = RequestMethod.POST)
    public ResponseData orderDelivery(@RequestBody List<OmsOrderDeliveryParam> deliveryParamList) {
        int count = omsOrderMapper.updateDelivery(deliveryParamList);
        //添加，操作记录表
        List<OmsOrderOperateHistory> operateHistoryList = deliveryParamList.stream()
                .map(omsOrderDeliveryParam -> {
                    OmsOrderOperateHistory history = new OmsOrderOperateHistory();
                    history.setOrderId(omsOrderDeliveryParam.getOrderId());
                    history.setCreateTime(new Date());
                    history.setOperateMan("后台管理员");
                    history.setOrderStatus(2);
                    history.setNote("完成发货");
                    return history;
                }).collect(Collectors.toList());
        omsOrderOperateHistoryMapper.insertList(operateHistoryList);
        return ResponseDataUtil.countJudge(count);
    }
    /*
        传参：
        {
            id: 12
            note: "哈哥哥备注"
            status: 4
        }
    */
    @ApiOperation("备注订单")
    @RequestMapping(value = "/update/note", method = RequestMethod.POST)
    public ResponseData updateNote(@RequestParam("id") Long id,
                                   @RequestParam("note") String note,
                                   @RequestParam("status") Integer status) {
        OmsOrder order = new OmsOrder();
        order.setId(id);
        order.setNote(note);
        order.setModifyTime(new Date());
        int count = omsOrderMapper.updateWay(order);
        //添加，操作记录表
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(id);
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(status);
        history.setNote("修改备注信息："+note);
        ArrayList<OmsOrderOperateHistory> omsOrderOperateHistories = new ArrayList<>();
        omsOrderOperateHistories.add(history);
        omsOrderOperateHistoryMapper.insertList(omsOrderOperateHistories);
        return ResponseDataUtil.countJudge(count);
    }
}
