package com.core.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

// 在使用：看CoreApplicationTests.java
@Component
@Validated // 数据校验
public class Dog {
    @NotNull(message="是否非空")
    @Value("旺仔")// spring注入值
    private String name;
    @Value("3")
    private Integer age;
    public Dog() {

    }
    public Dog(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
