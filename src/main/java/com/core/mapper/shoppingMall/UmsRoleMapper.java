package com.core.mapper.shoppingMall;

import com.core.pojo.shoppingMall.UmsAdmin;
import com.core.pojo.shoppingMall.UmsRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper // 扫描文件 mybatis的mapper类
@Repository
public interface UmsRoleMapper {
    List<UmsRole> selectWay(String name);
    int insertWay(UmsRole record);
    int updateWay(UmsRole record);
    int deleteWay(Long id);
}
