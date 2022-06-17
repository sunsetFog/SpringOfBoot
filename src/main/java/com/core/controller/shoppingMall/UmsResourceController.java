package com.core.controller.shoppingMall;

import com.core.common.util.ResponseData;
import com.core.common.util.ResponseDataUtil;
import com.core.mapper.shoppingMall.UmsResourceMapper;
import com.core.pojo.shoppingMall.UmsResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "UmsResourceController", description = "后台资源管理")
@RequestMapping("/resource")
public class UmsResourceController {
    @Autowired
    private UmsResourceMapper umsResourceMapper;

    @ApiOperation("查询所有后台资源")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseData resourceList() {
        List<UmsResource> umsResources = umsResourceMapper.selectWay();
        return ResponseDataUtil.buildSuccess(umsResources);
    }
}
