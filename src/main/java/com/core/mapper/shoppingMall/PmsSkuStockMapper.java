package com.core.mapper.shoppingMall;

import com.core.pojo.shoppingMall.PmsSkuStock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper // 扫描文件 mybatis的mapper类
@Repository
public interface PmsSkuStockMapper {
    int deleteWay(Long productId);
    int insertList(@Param("list") List<PmsSkuStock> skuStockList);
}
