package com.ydm.springboot.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties(prefix = "person")
@Validated//数据检验功能  和@ConfigurationProperties(prefix = "person")配合使用
public class Person {
//    @Email
    private int age;

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
