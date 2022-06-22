package com.core.controller.shoppingMall;

import com.core.common.util.ResponseData;
import com.core.common.util.ResponseDataUtil;
import com.core.mapper.shoppingMall.OmsOrderReturnReasonMapper;
import com.core.pojo.shoppingMall.OmsOrderReturnReason;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Api(tags = "OmsOrderReturnReasonController", description = "退货原因管理")
@RequestMapping("/returnReason")
public class OmsOrderReturnReasonController {
    @Autowired
    private OmsOrderReturnReasonMapper omsOrderReturnReasonMapper;

    @ApiOperation("分页查询退货原因")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseData returnReasonList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        List<OmsOrderReturnReason> omsOrderReturnReasons = omsOrderReturnReasonMapper.selectWay();
        return ResponseDataUtil.pageStructure(pageNum, pageSize, omsOrderReturnReasons);
    }
    /*
        传参：
        {
            "name": "太哈了",
            "sort": 0,
            "status": 1,
            "createTime": null
        }
    */
    @ApiOperation("添加退货原因")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseData returnReasonAdd(@RequestBody OmsOrderReturnReason omsOrderReturnReason) {
        omsOrderReturnReason.setCreateTime(new Date());
        int count = omsOrderReturnReasonMapper.insertWay(omsOrderReturnReason);
        return ResponseDataUtil.countJudge(count);
    }
    @ApiOperation("修改退货原因")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseData returnReasonUpdate(@RequestBody OmsOrderReturnReason omsOrderReturnReason) {
        int count = omsOrderReturnReasonMapper.updateWay(omsOrderReturnReason);
        return ResponseDataUtil.countJudge(count);
    }
    @ApiOperation("批量删除退货原因")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseData returnReasonDelate(@RequestParam("ids") List<Long> ids) {
        for (Long item: ids) {
            omsOrderReturnReasonMapper.deleteWay(item);
        }
        return ResponseDataUtil.buildSuccess(ids.size());
    }
}
