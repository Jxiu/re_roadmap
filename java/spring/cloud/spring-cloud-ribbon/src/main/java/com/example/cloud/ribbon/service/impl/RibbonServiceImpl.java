package com.example.cloud.ribbon.service.impl;

import com.example.cloud.ribbon.service.RibbonService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RibbonServiceImpl implements RibbonService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 使用eureka client ribbon 负载均衡不在使用原来的 ip:port来标识地址 而直接使用服务名称 ribbon根据
     *  获取到保存在eureka server注册表的service name 进行负载均衡 在进行远程调用的时候进行拦截后替换掉
     *  service name 到具体的ip:port
     * @return
     */
    @Override
    @HystrixCommand(fallbackMethod = "errorHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "4000")
    })
    public String info() {
        return restTemplate.getForObject("http://eureka-client/info",String.class);
    }

    @Override
    public String errorHandler() {
        return "Server Error";
    }


}
