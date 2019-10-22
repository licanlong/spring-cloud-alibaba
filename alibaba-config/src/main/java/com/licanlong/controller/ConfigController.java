package com.licanlong.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author licl
 * @date 2019/9/1
 */

@RestController
@RefreshScope
public class ConfigController {
    @Value("${config}")
    private String config;
    @Value("${current.env}")
    private String env;

    @GetMapping("/config")
    public String getConfig(){
        return config;
    }

    @GetMapping("/env")
    public String getCurrentEnv(){
        return env;
    }
}
