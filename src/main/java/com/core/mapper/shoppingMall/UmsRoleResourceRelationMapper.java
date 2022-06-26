package com.core.mapper.shoppingMall;

import com.core.pojo.shoppingMall.UmsResource;
import com.core.pojo.shoppingMall.UmsRoleResourceRelation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper // 扫描文件 mybatis的mapper类
@Repository
public interface UmsRoleResourceRelationMapper {
    int deleteWay(Long roleId);
    int insertList(UmsRoleResourceRelation umsRoleResourceRelation);
    /**
     * 根据角色ID获取资源
     */
    List<UmsResource> getResourceListByRoleId(Long roleId);
}
