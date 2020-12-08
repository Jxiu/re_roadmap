package com.example.cloud.feign.hystrix;

import com.example.cloud.feign.client.EurekaClientFeign;
import com.example.cloud.feign.domain.Parent;
import com.example.cloud.feign.domain.Student;
import org.springframework.stereotype.Component;

@Component
public class EurekaClientFeignHystrix implements EurekaClientFeign {

    @Override
    public String infoByFeign() {
        return "Server Error, Hystrix works for feign";
    }

    @Override
    public Student getStudentByFeign(String name, int age) {
        return null;
    }

    @Override
    public Student postStudentByFeign(Parent parent) {
        return null;
    }
}
