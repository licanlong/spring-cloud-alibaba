package com.licl;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author licl
 * @date 2019/9/29
 */
public interface HelloWorldService {
    @RequestMapping("/hello")
    String hello();
}
