package com.licanlong.feign.fallback;

import com.licanlong.feign.HelloService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author licl
 * @date 2019/9/1
 */
@Component
public class HelloServiceFallback implements HelloService{
    @Override
    public String hello() {
        return "HelloService调用失败";
    }
}
