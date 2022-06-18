package com.core.controller;

import com.core.common.util.ResponseData;
import com.core.common.util.ResponseDataUtil;
import com.core.mapper.shoppingMall.CmsPrefrenceAreaMapper;
import com.core.pojo.shoppingMall.CmsPrefrenceArea;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "CmsPrefrenceAreaController", description = "商品优选管理")
@RequestMapping("/prefrenceArea")
public class CmsPrefrenceAreaController {
    @Autowired
    private CmsPrefrenceAreaMapper cmsPrefrenceAreaMapper;

    @ApiOperation("获取所有商品优选")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseData prefrenceAreaList() {
        List<CmsPrefrenceArea> cmsPrefrenceAreas = cmsPrefrenceAreaMapper.selectWay();
        return ResponseDataUtil.buildSuccess(cmsPrefrenceAreas);
    }
}
