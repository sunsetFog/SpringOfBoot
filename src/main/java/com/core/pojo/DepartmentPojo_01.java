package com.core.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 部门表
@Data
@AllArgsConstructor // 有参
@NoArgsConstructor // 无参
public class DepartmentPojo_01 {
    private Integer id;
    private String departmentName;
}
