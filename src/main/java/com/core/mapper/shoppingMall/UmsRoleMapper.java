package com.core.mapper.shoppingMall;

import com.core.pojo.shoppingMall.UmsAdmin;
import com.core.pojo.shoppingMall.UmsRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Mapper // 扫描文件 mybatis的mapper类
@Repository
public interface UmsRoleMapper {
    /**
     * 通过用户账号查询角色集合
     * @param username 用户账号名称
     * @return Set<String>
     */
    @Select("select role_code from sys_role where id in (select role_id from sys_user_role where user_id = (select id from sys_user where username=#{username}))")
    Set<String> queryUserRoles(String username);
    List<UmsRole> selectWay(String name);
    int insertWay(UmsRole record);
    int updateWay(UmsRole record);
    int deleteWay(Long id);
}
