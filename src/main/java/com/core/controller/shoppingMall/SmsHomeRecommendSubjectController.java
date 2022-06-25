package com.core.controller.shoppingMall;

import com.core.common.util.ResponseData;
import com.core.common.util.ResponseDataUtil;
import com.core.mapper.shoppingMall.SmsHomeRecommendSubjectMapper;
import com.core.pojo.shoppingMall.SmsHomeRecommendSubject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "SmsHomeRecommendSubjectController", description = "首页专题推荐管理")
@RequestMapping("/homeRecommendSubject")
@CrossOrigin
public class SmsHomeRecommendSubjectController {
    @Autowired
    private SmsHomeRecommendSubjectMapper smsHomeRecommendSubjectMapper;

    @ApiOperation("分页查询推荐")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseData homeRecommendSubjectList(@RequestParam(value = "subjectName", required = false) String subjectName,
                                                 @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                 @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<SmsHomeRecommendSubject> smsHomeRecommendSubjects = smsHomeRecommendSubjectMapper.selectWay(subjectName, recommendStatus);
        return ResponseDataUtil.pageStructure(pageNum, pageSize, smsHomeRecommendSubjects);
    }
    /*
        传参：
            [
                {
                    "subjectId": 1,
                    "subjectName": "哈嘎226"
                }
            ]
    */
    @ApiOperation("批量添加首页推荐专题")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseData homeRecommendSubjectAdd(@RequestBody List<SmsHomeRecommendSubject> homeRecommendSubjectList) {
        for (SmsHomeRecommendSubject item: homeRecommendSubjectList) {
            item.setRecommendStatus(1);
            item.setSort(0);
            smsHomeRecommendSubjectMapper.insertWay(item);
        }
        return ResponseDataUtil.countJudge(homeRecommendSubjectList.size());
    }
    @ApiOperation("修改添加首页推荐专题")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseData homeRecommendSubjectUpdate(@RequestBody List<SmsHomeRecommendSubject> homeRecommendSubjectList) {
        for (SmsHomeRecommendSubject item: homeRecommendSubjectList) {
            smsHomeRecommendSubjectMapper.updateWay(item);
        }
        return ResponseDataUtil.countJudge(homeRecommendSubjectList.size());
    }
    @ApiOperation("批量删除推荐")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResponseData homeRecommendSubjectDelete(@RequestParam("ids") List<Long> ids) {
        int count = smsHomeRecommendSubjectMapper.deleteWay(ids);
        return ResponseDataUtil.countJudge(count);
    }
}
