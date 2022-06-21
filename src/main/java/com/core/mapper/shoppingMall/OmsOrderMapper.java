package com.core.mapper.shoppingMall;

import com.core.apiParams.OmsOrderListParam;
import com.core.pojo.shoppingMall.OmsOrder;
import com.core.pojo.shoppingMall.OmsOrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper // 扫描文件 mybatis的mapper类
@Repository
public interface OmsOrderMapper {
    List<OmsOrder> selectWay(OmsOrderListParam omsOrderListParam);
    OmsOrderDetail selectDetail(Long id);
    int update_deleteStatus(Long id, Integer deleteStatus);
}
