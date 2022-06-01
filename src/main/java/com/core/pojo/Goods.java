package com.core.pojo;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

/*
lombok之实体类：
@Data注解
自动给对象提供get, set, ToString, hashCode, equals等方法

原理：
java源文件---java语法树---根据lombok注解修改语法树---class字节码文件

只生成get, set, ToString
@Getter
@Setter
@ToString
*/
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor// 无参构造
@AllArgsConstructor// 全参构造
@Accessors(chain = true)// 开启链式调用 就可以写法user.setName(77).setAge(18)
@ApiModel("商品实体类")
public class Goods {
    private int id;
    private String name;
    private String imgUrl;
    private Date create_time;
    private Date update_time;
}
