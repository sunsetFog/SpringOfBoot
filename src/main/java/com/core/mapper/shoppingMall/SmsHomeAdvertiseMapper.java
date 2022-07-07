package com.core.mapper.shoppingMall;

import com.core.pojo.shoppingMall.SmsHomeAdvertise;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper // 扫描文件 mybatis的mapper类
@Repository
public interface SmsHomeAdvertiseMapper {
    List<SmsHomeAdvertise> selectWay(String name, Integer type, String endTime);
    int insertWay(SmsHomeAdvertise smsHomeAdvertise);
    int updateWay(SmsHomeAdvertise smsHomeAdvertise);
    int deleteWay(List<Long> ids);
}
