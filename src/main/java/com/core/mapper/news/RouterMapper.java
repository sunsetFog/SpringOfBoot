package com.core.mapper.news;

import com.core.pojo.news.Router.Operation;
import com.core.pojo.news.Router.Router;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RouterMapper {
    List<Router> isRouterList(String username);
    List<Operation> isOperationList(String username);
}
