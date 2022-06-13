package com.core.common.AnalogData;

import com.core.pojo.DepartmentPojo_01;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

// 部门dao
@Repository // 托管
public class MapDepartmentDao_01 {
    // 模拟数据库中的数据
    private static Map<Integer, DepartmentPojo_01> newTable = null;
    static {
        newTable = new HashMap<Integer, DepartmentPojo_01>(); // 创建一个部门表
        newTable.put(101, new DepartmentPojo_01(101, "W教学部"));
        newTable.put(102, new DepartmentPojo_01(102, "W市场部"));
        newTable.put(103, new DepartmentPojo_01(103, "E教研部"));
        newTable.put(104, new DepartmentPojo_01(104, "E运营部"));
        newTable.put(105, new DepartmentPojo_01(105, "E后勤部"));
    }
    // 获得所有部门数据
    public Collection<DepartmentPojo_01> getDepartments() {
        return newTable.values();
    }
    // 通过id得到部门
    public DepartmentPojo_01 getDepartments(Integer id) {
        return newTable.get(id);
    }
}
