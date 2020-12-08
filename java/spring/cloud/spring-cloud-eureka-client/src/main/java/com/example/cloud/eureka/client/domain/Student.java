package com.example.cloud.eureka.client.domain;

import lombok.Data;

@Data
public class Student {

    private int id;
    private String name;
    private int age;
    private Parent parent;

}
