package com.example.cloud.ribbon.contoller;

import com.example.cloud.ribbon.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RibbonController {

    @Autowired
    private RibbonService ribbonService;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/info")
    public String info(){
        return ribbonService.info();
    }

    @GetMapping("serviceInfo")
    public String serviceInfo(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client");
        return "port: " + serviceInstance.getPort();
    }
}
