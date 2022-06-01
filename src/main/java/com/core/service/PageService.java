package com.core.service;

import com.core.mapper.GoodsMapper;
import com.core.pojo.Goods;
import com.core.pojo.PageParams;
import com.core.common.util.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/*
Spring Boot：实现MyBatis分页
https://www.cnblogs.com/xifengxiaoma/p/11027551.html
*/
@Service
public class PageService {
    @Autowired
    private GoodsMapper goodsMapper;
    public PageResult queryPage(@RequestBody PageParams pageParams, HttpServletRequest request) {

        return getPageResult(pageParams, getPageInfo(pageParams));
    }
    private PageInfo<Goods> getPageInfo(PageParams pageParams) {
        int pageNum = pageParams.getPageNum();
        int pageSize = pageParams.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> goodsList = goodsMapper.goodsQueryList(pageParams);
        System.out.println("--pageNum--"+ pageNum);
        System.out.println("--pageSize--"+ pageSize);
        return new PageInfo<Goods>(goodsList);
    }
    public static PageResult getPageResult(PageParams pageParams, PageInfo<?> pageInfo) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }
}
