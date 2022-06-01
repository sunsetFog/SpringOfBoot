package com.core.mapper;

import com.core.pojo.Goods;
import com.core.pojo.PageParams;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
// 实体类
@Mapper // 扫描文件 mybatis的mapper类
@Repository
public interface GoodsMapper {
    // 分页查询
    List<Goods> goodsQueryList(PageParams pageParams);
    // 上图片改字段
    int uploadGoods(Goods record);
    // 添加
    int insertSelective(Goods record);
    // 查询指定单条数据
    Goods selectByPrimaryKey(Integer id);
    // 删除
    int deleteGoods(Integer id);
    // 编辑修改
    int updateGoods(Goods record);
}
