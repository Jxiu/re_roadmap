package com.example.cloud.feign.service.impl;

import com.example.cloud.feign.client.EurekaClientFeign;
import com.example.cloud.feign.domain.Parent;
import com.example.cloud.feign.domain.Student;
import com.example.cloud.feign.service.Myservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MyServiceImpl implements Myservice {

    @Resource
    private EurekaClientFeign eurekaClientFeign;

    @Override
    public String infoByFeign() {
        return eurekaClientFeign.infoByFeign();
    }

    @Override
    public Student getStudentByFeign(String name, int age) {
        return eurekaClientFeign.getStudentByFeign(name, age);
    }

    @Override
    public Student postStudentByFeign(Parent parent) {
        return eurekaClientFeign.postStudentByFeign(parent);
    }
}
