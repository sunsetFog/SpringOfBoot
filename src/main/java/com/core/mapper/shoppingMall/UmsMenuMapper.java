package com.core.mapper.shoppingMall;

import com.core.pojo.shoppingMall.UmsMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper // 扫描文件 mybatis的mapper类
@Repository
public interface UmsMenuMapper {
    List<UmsMenu> selectWay();
}
