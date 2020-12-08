package com.example.cloud.eureka.client.controller;

import com.example.cloud.eureka.client.domain.Parent;
import com.example.cloud.eureka.client.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Slf4j
@RestController
public class MyController {

    @Value("${student.info.name}")
    private String name;
    @Value("${student.info.address}")
    private String address;

    @GetMapping("/info")
    public String info(){
        int randomInt = new Random().nextInt(5500);
        log.info("thread should sleep {} ms", randomInt);
        try {
            Thread.sleep((long)randomInt);
        } catch (InterruptedException e) {
            log.info("info method error", e);
        }
        String info = "welcome:" + this.name + ",you address is " + this.address;
        return info;
    }

    @GetMapping("/getStudent")
    public Student getStudentByGetRequest(@RequestParam("name") String name,
                                          @RequestParam("age") int age,
                                          HttpServletRequest request){
        log.info("authKey: {}", request.getHeader("authKey"));
        log.info("name:{} ,age:{}",name,age);
        Parent parent = new Parent();
        parent.setAddress("parent address");
        parent.setId(10);
        parent.setName("parent");

        Student student = new Student();
        student.setAge(12);
        student.setId(2);
        student.setName("student");
        student.setParent(parent);
        return student;
    }

    @PostMapping("/postStudent")
    public Student getStudentByPostRequest(@RequestBody Parent parent,
                                           HttpServletRequest request){
        log.info("authKey: {}", request.getHeader("authKey"));
        log.info("parent: {}", parent);
        Student student = new Student();
        student.setAge(11);
        student.setId(5);
        student.setName("theStudent");
        student.setParent(parent);
        return student;
    }
}
