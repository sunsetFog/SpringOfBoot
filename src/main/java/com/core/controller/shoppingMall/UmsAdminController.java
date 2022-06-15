package com.core.controller.shoppingMall;

import com.core.apiParams.UmsAdminParam;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
    /*
        添加用户
        加参数校验
    */
    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseData register(@Validated @RequestBody UmsAdminParam umsAdminParam) {
        System.out.println("--UmsAdminParam参数校验类--"+umsAdminParam);
        UmsAdmin umsAdmin = new UmsAdmin();
        // 两个类复制
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        // 默认字段
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        System.out.println("--UmsAdmin实体类--"+umsAdmin);
        /*
            1.不能相同用户名：
                umsAdmin.getUsername()用户名SQL查询
                有一条就return不许再SQL插入
                if (umsAdminList.size() > 0) {
                    return null;
                }

            2.密码加密，然后SQL插入数据库
                umsAdmin.getPassword()加密
                umsAdmin.setPassword(encodePassword);
        */

        // SQL插入
        umsAdminMapper.insertWay(umsAdmin);
        return ResponseDataUtil.buildSuccess(umsAdmin);
    }
    @ApiOperation("修改指定用户信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseData adminUpdate(@RequestBody UmsAdmin umsAdmin) {
        int count = umsAdminMapper.updateWay(umsAdmin);
        if (count > 0) {
            return ResponseDataUtil.buildSuccess(count);
        } else {
            return ResponseDataUtil.buildError();
        }
    }
    @ApiOperation("删除指定用户信息")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ResponseData adminDelete(@PathVariable Long id) {
        int count = umsAdminMapper.deleteWay(id);
        if (count > 0) {
            return ResponseDataUtil.buildSuccess(count);
        } else {
            return ResponseDataUtil.buildError();
        }
    }

}
