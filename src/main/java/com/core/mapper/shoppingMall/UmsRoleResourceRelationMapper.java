package com.core.mapper.shoppingMall;

import com.core.pojo.shoppingMall.UmsRoleResourceRelation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper // 扫描文件 mybatis的mapper类
@Repository
public interface UmsRoleResourceRelationMapper {
    int deleteWay(Long roleId);
    int insertList(UmsRoleResourceRelation umsRoleResourceRelation);
}
