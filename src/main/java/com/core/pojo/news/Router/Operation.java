package com.core.pojo.news.Router;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("路由2实体类")
public class Operation {
    private int id;
    private String operation_code;
    private String operation_name;
    private Boolean disabled;
    private Boolean hidden;
}
