package com.core.mapper.news;

import com.core.pojo.news.LoginParams;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    LoginParams loginVerify(String username, String password);
}
