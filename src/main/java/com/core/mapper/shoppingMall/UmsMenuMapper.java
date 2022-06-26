package com.core.mapper.shoppingMall;

import com.core.pojo.shoppingMall.UmsMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper // 扫描文件 mybatis的mapper类
@Repository
public interface UmsMenuMapper {
    List<UmsMenu> treeList();
    List<UmsMenu> selectWay(Long parentId);
    int insertWay(UmsMenu umsMenu);
    int updateWay(UmsMenu umsMenu);
    int deleteWay(Long id);
}
