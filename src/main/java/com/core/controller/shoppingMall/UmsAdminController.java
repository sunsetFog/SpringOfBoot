package com.core.controller.shoppingMall;

import com.core.common.util.PageResult;
import com.core.common.util.ResponseData;
import com.core.common.util.ResponseDataUtil;
import com.core.mapper.shoppingMall.UmsAdminMapper;
import com.core.pojo.Goods;
import com.core.pojo.shoppingMall.UmsAdmin;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {
    @Autowired
    private UmsAdminMapper umsAdminMapper;

    /*
        分页查询,pageSize传9999查所有
        username和nick_name字段模糊搜索
    */
    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @PostMapping("/list")
    public ResponseData adminList(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        keyword = '%' + keyword + '%';
        // SQL查询
        List<UmsAdmin> adminList = umsAdminMapper.selectWay(keyword);
        System.out.println("--adminList--"+adminList);
        // 分页
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<UmsAdmin> goodsPageInfo = new PageInfo<UmsAdmin>(adminList);
        PageResult pageResult = PageResult.getPageResult(goodsPageInfo);
        return ResponseDataUtil.buildSuccess(pageResult);
    }
}
