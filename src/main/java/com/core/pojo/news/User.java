package com.core.pojo.news;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor // 无参构造
@AllArgsConstructor // 有参构造
@ApiModel("用户实体类") // Swaggwer注解   或@Api("**")
public class User implements Serializable {// 实体类序列化
    private int id;
    @ApiModelProperty("用户名") // Swaggwer注解
    private String username;
    @ApiModelProperty("密码") // Swaggwer注解
    private String password;
//    private String perms;
}
