package com.example.cloud.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 *  1.服务注册
 *  2.服务续约 心跳机制
 *  3.服务同步 集群多个实例相互同步
 *  4.客户端从注册中心拉起服务列表 服务发现
 *  5.客户端互相调用
 *  6.服务的剔除 服务端后端定时剔除（90s）没有续约的客户端
 *  7.服务离开 客户端（服务端实例之间互为客户端）下线
 *  8.服务端自我保护机制 网络波动造成短时间大量的客户端下线 服务器暂时保护客户端注册信息不被剔除
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class);
    }
}
