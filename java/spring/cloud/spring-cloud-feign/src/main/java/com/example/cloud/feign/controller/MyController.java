package com.example.cloud.feign.controller;

import com.example.cloud.feign.domain.Parent;
import com.example.cloud.feign.domain.Student;
import com.example.cloud.feign.service.Myservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private Myservice myservice;

    @GetMapping("/infoByFeign")
    public String infoByFeign(){
        return myservice.infoByFeign();
    }

    @GetMapping(value = "/getStudent", produces = "application/json;charset=UTF-8")
    public Student getStudentByFeign(){
        return myservice.getStudentByFeign("zhangsan", 11);
    }

    @PostMapping(value = "/postStudent", produces = "application/json;charset=UTF-8")
    public Student postStudentByFeign(){
        Parent parent = new Parent();
        parent.setId(11);
        parent.setName("冰冰");
        parent.setAddress("吉林");
        return myservice.postStudentByFeign(parent);
    }
}
