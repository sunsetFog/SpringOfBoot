package com.core.controller.shoppingMall;

import com.core.common.util.PageResult;
import com.core.common.util.ResponseData;
import com.core.common.util.ResponseDataUtil;
import com.core.mapper.shoppingMall.UmsMenuMapper;
import com.core.pojo.shoppingMall.UmsAdmin;
import com.core.pojo.shoppingMall.UmsMenu;
import com.core.pojo.shoppingMall.UmsMenuNode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@Api(tags = "UmsMenuController", description = "后台菜单管理")
@RequestMapping("/menu")
public class UmsMenuController {
    @Autowired
    private UmsMenuMapper umsMenuMapper;

    @ApiOperation("树形结构返回所有菜单列表")
    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    public ResponseData treeList() {
        List<UmsMenu> menuList = umsMenuMapper.selectWay(null);
        /*
            list.stream.collect(Collectors.toList())去重
        */
        List<UmsMenuNode> result = menuList.stream()
                .filter(menu -> menu.getParentId().equals(0L))
                .map(menu -> covertMenuNode(menu, menuList)).collect(Collectors.toList());
        return ResponseDataUtil.buildSuccess(result);
    }
    /**
     * 将UmsMenu转化为UmsMenuNode并设置children属性
     */
    private UmsMenuNode covertMenuNode(UmsMenu menu, List<UmsMenu> menuList) {
        UmsMenuNode node = new UmsMenuNode();
        BeanUtils.copyProperties(menu, node);
        List<UmsMenuNode> children = menuList.stream()
                .filter(subMenu -> subMenu.getParentId().equals(menu.getId()))
                .map(subMenu -> covertMenuNode(subMenu, menuList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
    /*
        分页查询,pageSize传9999查所有
        parentId条件筛选
    */
    @ApiOperation("分页查询后台菜单")
    @PostMapping("/list")
    public ResponseData menuList(@RequestParam(value = "parentId", defaultValue = "0") Long parentId,
                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        // SQL查询
        List<UmsMenu> umsMenus = umsMenuMapper.selectWay(parentId);
        // 分页
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<UmsMenu> goodsPageInfo = new PageInfo<UmsMenu>(umsMenus);
        PageResult pageResult = PageResult.getPageResult(goodsPageInfo);
        return ResponseDataUtil.buildSuccess(pageResult);
    }
    /*
        传参：
            {
                "title": "呃呃呃",
                "parentId": 7,
                "name": "嗡嗡嗡",
                "icon": "我11",
                "hidden": 0,
                "sort": 0
            }
    */
    @ApiOperation("添加后台菜单")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseData menuAdd(@RequestBody UmsMenu umsMenu) {
        umsMenu.setCreateTime(new Date());
        updateLevel(umsMenu);
        // SQL插入
        int count = umsMenuMapper.insertWay(umsMenu);
        if (count > 0) {
            return ResponseDataUtil.buildSuccess(count);
        } else {
            return ResponseDataUtil.buildError();
        }
    }

    @ApiOperation("修改后台菜单")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseData menuUpdate(@RequestBody UmsMenu umsMenu) {
        updateLevel(umsMenu);
        // SQL修改
        int count = umsMenuMapper.updateWay(umsMenu);
        if (count > 0) {
            return ResponseDataUtil.buildSuccess(count);
        } else {
            return ResponseDataUtil.buildError();
        }
    }
    /**
     * setLevel修改菜单层级
     */
    private void updateLevel(UmsMenu umsMenu) {
        if (umsMenu.getParentId() == 0) {
            //没有父菜单时为一级菜单
            umsMenu.setLevel(0);
        } else {
            //有父菜单时,根据parentId进行SQL查询，得出一条数据，获得level值
//            UmsMenu parentMenu = umsMenuMapper.selectWay(umsMenu.getParentId());
            List<UmsMenu> umsMenus = umsMenuMapper.selectWay(umsMenu.getParentId());
            for(UmsMenu item: umsMenus) {
                if (item != null) {
                    umsMenu.setLevel(item.getLevel() + 1);
                } else {
                    umsMenu.setLevel(0);
                }
            }
        }
    }
    @ApiOperation("根据ID删除后台菜单")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ResponseData menuDelete(@PathVariable Long id) {
        int count = umsMenuMapper.deleteWay(id);
        if (count > 0) {
            return ResponseDataUtil.buildSuccess(count);
        } else {
            return ResponseDataUtil.buildError();
        }
    }
}
