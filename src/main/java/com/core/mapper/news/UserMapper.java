package com.core.mapper.news;

import com.core.pojo.news.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    User loginVerify(String username, String password);
}
