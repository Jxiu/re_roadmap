package com.example.cloud.feign.client;

import com.example.cloud.feign.config.FeignClientConfig;
import com.example.cloud.feign.config.FeignClientInterceptorConfig;
import com.example.cloud.feign.domain.Parent;
import com.example.cloud.feign.domain.Student;
import com.example.cloud.feign.hystrix.EurekaClientFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "eureka-client" ,
        configuration = {FeignClientConfig.class, FeignClientInterceptorConfig.class},
        fallback = EurekaClientFeignHystrix.class)
public interface EurekaClientFeign {

    @GetMapping(value = "/info")
    String infoByFeign();

    @GetMapping(value = "getStudent")
    Student getStudentByFeign(@RequestParam("name") String name,
                              @RequestParam("age") int age);

    @PostMapping(value = "postStudent")
    Student postStudentByFeign(@RequestBody Parent parent);
}
