package com.core.mapper.shoppingMall;

import com.core.apiParams.SmsCouponAddParam;
import com.core.pojo.shoppingMall.SmsCoupon;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper // 扫描文件 mybatis的mapper类
@Repository
public interface SmsCouponMapper {
    List<SmsCouponAddParam> selectWay(String name, Integer type);
    SmsCouponAddParam selectRow(Long id);
    int insertWay(SmsCoupon smsCoupon);
    int updateWay(SmsCoupon smsCoupon);
    int deleteWay(Long id);
}
