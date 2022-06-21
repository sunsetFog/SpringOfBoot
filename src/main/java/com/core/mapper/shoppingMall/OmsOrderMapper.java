package com.core.mapper.shoppingMall;

import com.core.apiParams.OmsOrderDeliveryParam;
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
    int updateWay(OmsOrder omsOrder);
    int update_deleteStatus(Long id, Integer deleteStatus);
    /**
     * 批量发货
     */
    int updateDelivery(@Param("list") List<OmsOrderDeliveryParam> deliveryParamList);
}
