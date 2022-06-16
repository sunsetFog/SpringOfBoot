package com.core.controller.shoppingMall;

import com.core.common.util.ResponseData;
import com.core.common.util.ResponseDataUtil;
import com.core.mapper.shoppingMall.UmsMenuMapper;
import com.core.pojo.shoppingMall.UmsMenu;
import com.core.pojo.shoppingMall.UmsMenuNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        List<UmsMenu> menuList = umsMenuMapper.selectWay();
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
}
