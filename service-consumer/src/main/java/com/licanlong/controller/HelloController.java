package com.licanlong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author licl
 * @date 2019/8/31
 */
@RestController
public class HelloController {
    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("/hello")
    public String hello(){
        return restTemplate.getForObject("http://service-provider/hello", String.class);
    }
}
