package com.core.mapper.shoppingMall;

import com.core.apiParams.OmsOrderReturnApplyListParam;
import com.core.pojo.shoppingMall.OmsOrderReturnApply;
import com.core.pojo.shoppingMall.OmsOrderReturnApplyResult;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper // 扫描文件 mybatis的mapper类
@Repository
public interface OmsOrderReturnApplyMapper {
    /**
     * 查询申请列表
     */
    List<OmsOrderReturnApply> selectWay(OmsOrderReturnApplyListParam omsOrderReturnApplyListParam);
    /**
     * 获取申请详情
     */
    OmsOrderReturnApplyResult selectById(Long id);
    int updateWay(OmsOrderReturnApply omsOrderReturnApply);
}
