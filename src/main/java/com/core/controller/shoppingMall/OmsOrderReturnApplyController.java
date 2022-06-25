package com.core.controller.shoppingMall;

import com.core.apiParams.OmsOrderReturnApplyListParam;
import com.core.apiParams.OmsOrderReturnApplyStatusParam;
import com.core.common.util.ResponseData;
import com.core.common.util.ResponseDataUtil;
import com.core.mapper.shoppingMall.OmsOrderReturnApplyMapper;
import com.core.pojo.shoppingMall.OmsOrderReturnApply;
import com.core.pojo.shoppingMall.OmsOrderReturnApplyResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Api(tags = "OmsOrderReturnApplyController", description = "订单退货申请管理")
@RequestMapping("/returnApply")
@CrossOrigin
public class OmsOrderReturnApplyController {
    @Autowired
    private OmsOrderReturnApplyMapper omsOrderReturnApplyMapper;
    /*
        传参：
        {
            "id": 3,
            "status": 0,
            "createTime": "2018-10-17",
            "handleMan": null,
            "handleTime": null,
            "pageNum": 1,
            "pageSize": 10
        }
    */
    @ApiOperation("分页查询退货申请")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseData returnApplyList(@RequestBody OmsOrderReturnApplyListParam omsOrderReturnApplyListParam) {
        List<OmsOrderReturnApply> omsOrderReturnApplies = omsOrderReturnApplyMapper.selectWay(omsOrderReturnApplyListParam);
        return ResponseDataUtil.pageStructure(omsOrderReturnApplyListParam.getPageNum(), omsOrderReturnApplyListParam.getPageSize(), omsOrderReturnApplies);
    }

    @ApiOperation("获取退货申请详情")
    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public ResponseData returnApplyDetails(@PathVariable Long id) {
        OmsOrderReturnApplyResult omsOrderReturnApplyResult = omsOrderReturnApplyMapper.selectById(id);
        return ResponseDataUtil.buildSuccess(omsOrderReturnApplyResult);
    }
    /*
        传参：
        {
            "id": 3,
            "companyAddressId": 1,
            "handleMan": "admin",
            "handleNote": null,
            "receiveMan": "admin",
            "receiveNote": null,
            "returnAmount": 0,
            "status": 1
        }
    */
    @ApiOperation("修改退货申请状态")
    @RequestMapping(value = "/update/status", method = RequestMethod.POST)
    public ResponseData returnApplyStatus(@RequestBody OmsOrderReturnApplyStatusParam statusParam) {
        Integer status = statusParam.getStatus();
        OmsOrderReturnApply returnApply = new OmsOrderReturnApply();
        if (status.equals(1)) {
            //确认退货
            returnApply.setId(statusParam.getId());
            returnApply.setStatus(1);
            returnApply.setReturnAmount(statusParam.getReturnAmount());
            returnApply.setCompanyAddressId(statusParam.getCompanyAddressId());
            returnApply.setHandleTime(new Date());
            returnApply.setHandleMan(statusParam.getHandleMan());
            returnApply.setHandleNote(statusParam.getHandleNote());
        } else if (status.equals(2)) {
            //完成退货
            returnApply.setId(statusParam.getId());
            returnApply.setStatus(2);
            returnApply.setReceiveTime(new Date());
            returnApply.setReceiveMan(statusParam.getReceiveMan());
            returnApply.setReceiveNote(statusParam.getReceiveNote());
        } else if (status.equals(3)) {
            //拒绝退货
            returnApply.setId(statusParam.getId());
            returnApply.setStatus(3);
            returnApply.setHandleTime(new Date());
            returnApply.setHandleMan(statusParam.getHandleMan());
            returnApply.setHandleNote(statusParam.getHandleNote());
        } else {
            return ResponseDataUtil.countJudge(0);
        }
        int count = omsOrderReturnApplyMapper.updateWay(returnApply);
        return ResponseDataUtil.countJudge(count);
    }
}
