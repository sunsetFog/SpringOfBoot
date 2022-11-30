package com.stars.mapper.news;

import com.stars.pojo.news.LoginParams;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    LoginParams loginVerify(String username, String password);
}
