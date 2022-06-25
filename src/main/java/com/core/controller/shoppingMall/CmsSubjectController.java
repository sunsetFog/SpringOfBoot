package com.core.controller.shoppingMall;

import com.core.common.util.ResponseData;
import com.core.common.util.ResponseDataUtil;
import com.core.mapper.shoppingMall.CmsSubjectMapper;
import com.core.pojo.shoppingMall.CmsSubject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "CmsSubjectController", description = "商品专题管理")
@RequestMapping("/subject")
@CrossOrigin
public class CmsSubjectController {
    @Autowired
    private CmsSubjectMapper cmsSubjectMapper;

    @ApiOperation("获取全部商品专题")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseData subjectList() {
        List<CmsSubject> cmsSubjects = cmsSubjectMapper.selectWay();
        return ResponseDataUtil.buildSuccess(cmsSubjects);
    }
}
