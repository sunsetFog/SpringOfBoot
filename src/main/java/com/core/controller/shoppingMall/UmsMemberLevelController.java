package com.core.controller.shoppingMall;

import com.core.common.util.ResponseData;
import com.core.common.util.ResponseDataUtil;
import com.core.mapper.shoppingMall.UmsMemberLevelMapper;
import com.core.pojo.shoppingMall.UmsMemberLevel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "UmsMemberLevelController", description = "会员等级管理")
@RequestMapping("/memberLevel")
@CrossOrigin
public class UmsMemberLevelController {
    @Autowired
    private UmsMemberLevelMapper umsMemberLevelMapper;

    @ApiOperation("查询所有会员等级")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseData memberLevelList(@RequestParam("defaultStatus") Integer defaultStatus) {
        List<UmsMemberLevel> umsMemberLevels = umsMemberLevelMapper.selectWay(defaultStatus);
        return ResponseDataUtil.buildSuccess(umsMemberLevels);
    }
}
