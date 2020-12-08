package com.example.cloud.feign.service;

import com.example.cloud.feign.domain.Parent;
import com.example.cloud.feign.domain.Student;

public interface Myservice {
    String infoByFeign();
    Student getStudentByFeign(String name, int age);
    Student postStudentByFeign(Parent parent);
}
