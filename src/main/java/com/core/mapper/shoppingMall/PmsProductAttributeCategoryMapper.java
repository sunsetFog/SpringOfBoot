package com.core.mapper.shoppingMall;

import com.core.pojo.shoppingMall.PmsProductAttributeCategory;
import com.core.pojo.shoppingMall.PmsProductAttributeCategoryItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper // 扫描文件 mybatis的mapper类
@Repository
public interface PmsProductAttributeCategoryMapper {
    List<PmsProductAttributeCategory> selectWay();
    // 通过id，SQL查询一条数据
    PmsProductAttributeCategory selectById(Long id);
    /**
     * 获取包含属性的商品属性分类
     */
    List<PmsProductAttributeCategoryItem> selectCategoryAndAttribute();
    int insertWay(PmsProductAttributeCategory pmsProductAttributeCategory);
    int updateWay(PmsProductAttributeCategory pmsProductAttributeCategory);
    int deleteWay(Long id);
}
