package com.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
/*
    JDBC操作Mysql
    没有实体类，数据库中的东西用Map取
*/
@RestController
public class JDBCController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    /*
        查询表所有
    */
    @GetMapping("/store/list")
    public List<Map<String,Object>> storeList() {
        String sql = "select * from goods";
        List<Map<String, Object>> list_maps = jdbcTemplate.queryForList(sql);
        return list_maps;
    }

    @GetMapping("/store/add")
    public String storeAdd() {
        String sql = "insert into db_news.goods(id,name,date) values (135,'二哈','2022.02.06')";
        jdbcTemplate.update(sql);
        return "add-ok";
    }

    @GetMapping("store/update/{id}")
    public String storeUpdate(@PathVariable("id") int id) {
        String sql = "update db_news.goods set name=?,date =? where id="+id;
        Object[] objects = new Object[2];
        objects[0] = "嘿嘿";
        objects[1] = "2022-2-8";
        jdbcTemplate.update(sql, objects);
        return "update-ok";
    }

    @GetMapping("store/delete/{id}")
    public String storeDelete(@PathVariable("id") int id) {
        String sql = "delete from db_news.goods where id=?";
        jdbcTemplate.update(sql,id);
        return "delete-ok";
    }
}
