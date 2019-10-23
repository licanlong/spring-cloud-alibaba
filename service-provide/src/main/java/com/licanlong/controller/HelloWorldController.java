package com.licanlong.controller;

import com.licl.HelloWorldService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author licl
 * @date 2019/8/31
 */
@RestController
public class HelloWorldController implements HelloWorldService {
    @Value("${server.port}")
    private String port;

    @Override
    public String hello() {
        return "hello"+port;
    }
}
