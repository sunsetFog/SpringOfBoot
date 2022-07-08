package com.core.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 部门表
@Data
//@AllArgsConstructor // 有参
//@NoArgsConstructor // 无参
public class DepartmentPojo_01 {
    private Integer id;
    private String departmentName;

    public DepartmentPojo_01(Integer id, String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
