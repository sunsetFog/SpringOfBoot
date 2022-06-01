package com.core.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
// 在使用：看CoreApplicationTests.java
@Component
@PropertySource(value = "classpath:cat.properties") // 加载指定的properties配置文件
public class Cat {
    @Value("${name}")// 绑定properties配置文件的值
    private String name;
    @Value("${age}")
    private Integer age;
    public Cat() {

    }
    public Cat(String name, Integer age) {
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
