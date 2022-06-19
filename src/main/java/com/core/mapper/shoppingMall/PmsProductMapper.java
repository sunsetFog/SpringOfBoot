package com.core.mapper.shoppingMall;

import com.core.apiParams.PmsProductAddParam;
import com.core.apiParams.PmsProductListParam;
import com.core.pojo.shoppingMall.PmsProduct;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper // 扫描文件 mybatis的mapper类
@Repository
public interface PmsProductMapper {
    List<PmsProduct> selectWay(PmsProductListParam productQueryParam);
    int insertWay(PmsProductAddParam pmsProductAddParam);
}
