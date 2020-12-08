package com.example.cloud.ribbon.config;

import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RibbonConfig {

    @Bean
    @LoadBalanced //是restTemplate拥有负载均衡能力
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /**
     * 指定负载均衡规则
     * @return
     */
    @Bean
    public RoundRobinRule roundRobinRule(){
        return new RoundRobinRule();
    }
}
