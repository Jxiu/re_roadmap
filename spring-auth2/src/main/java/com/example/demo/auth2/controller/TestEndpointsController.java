package com.example.demo.auth2.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jxiu
 * @date 2021/2/9
 */

@RestController
public class TestEndpointsController {
    @RequestMapping("api/{id}")
    public String getApi(@PathVariable() String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "api is " + id;
    }
    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "order id :" + id;
    }
}
