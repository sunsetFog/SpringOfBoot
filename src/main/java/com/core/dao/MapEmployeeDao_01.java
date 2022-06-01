package com.core.dao;

import com.core.pojo.DepartmentPojo_01;
import com.core.pojo.MapEmployeePojo_01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

// 员工Dao
@Repository // 托管
public class MapEmployeeDao_01 {
    // 模拟数据库中的数据
    private static Map<Integer, MapEmployeePojo_01> newTable = null;
    // 员工所属的部门
    @Autowired // 注解
    private MapDepartmentDao_01 mapDepartmentDao01;
    static {
        newTable = new HashMap<Integer, MapEmployeePojo_01>(); // 创建一个员工表
        newTable.put(1001, new MapEmployeePojo_01(1001, "小白", "1929288@qq.com", 0, new DepartmentPojo_01(101, "A教学部")));
        newTable.put(1002, new MapEmployeePojo_01(1002, "小兔", "2929288@qq.com", 0, new DepartmentPojo_01(102, "B市场部")));
        newTable.put(1003, new MapEmployeePojo_01(1003, "小花", "3929288@qq.com", 1, new DepartmentPojo_01(103, "C教研部")));
        newTable.put(1004, new MapEmployeePojo_01(1004, "小猫", "4929288@qq.com", 0, new DepartmentPojo_01(104, "D运营部")));
        newTable.put(1005, new MapEmployeePojo_01(1005, "小狗", "5929288@qq.com", 0, new DepartmentPojo_01(105, "E后勤部")));
    }
    // 主键自增
    private static Integer initId = 1006;
    // 增加一个员工
    public void save(MapEmployeePojo_01 mapEmployeePojo01) {
        if (mapEmployeePojo01.getId() == null) {
            mapEmployeePojo01.setId(initId++);
        }
        mapEmployeePojo01.setDepartmentPojo01(
                mapDepartmentDao01.getDepartments(
                    mapEmployeePojo01.getDepartmentPojo01().getId()
                )
        );
        newTable.put(mapEmployeePojo01.getId(), mapEmployeePojo01);
    }
    // 查询全部员工信息
    public Collection<MapEmployeePojo_01> getAll(){
        return newTable.values();
    }
    // 通过id查询员工
    public MapEmployeePojo_01 getEmployeeById(Integer id){
        return newTable.get(id);
    }
    //  通过id删除员工
    public void delete(Integer id) {
        newTable.remove(id);
    }
}
