package com.core.pojo.news.Router;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("路由2实体类")
public class Router {
    private int id;
    private int parentId;
    private String path;
    private String metaTitle;
    private int metaKey;
    @ApiModelProperty("导入路径")
    private String importPath;
}
