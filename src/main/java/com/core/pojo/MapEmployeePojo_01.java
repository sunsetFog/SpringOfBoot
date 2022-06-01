package com.core.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

// 员工表
@Data
//@AllArgsConstructor // 有参构造
@NoArgsConstructor // 无参构造
@ApiModel("用户实体类")
public class MapEmployeePojo_01 {
    private Integer id;
    @ApiModelProperty("名字")
    private String lastName;
    private String email;
    private Integer gender; // 0:女  1:男
    private DepartmentPojo_01 departmentPojo01;
    private Date birth;

    public MapEmployeePojo_01(Integer id, String lastName, String email, Integer gender, DepartmentPojo_01 departmentPojo01) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.departmentPojo01 = departmentPojo01;
        this.birth = birth;
//        this.birth = new Date(); // 默认创建日期
    }
}
