package com.licl.oauth2server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author licl
 * @date 2019/8/31
 */
@RestController
public class HelloWorldController {
    @Value("${server.port}")
    private String port;


    @RequestMapping("/hello")
    public String hello() throws InterruptedException {
        return "hello"+port;
    }
}
