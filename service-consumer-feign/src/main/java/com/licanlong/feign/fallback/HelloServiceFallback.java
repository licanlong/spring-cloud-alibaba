package com.licanlong.feign.fallback;

import com.licanlong.feign.HelloService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author licl
 * @date 2019/9/1
 */
@Component
public class HelloServiceFallback implements FallbackFactory<HelloService>{
    @Override
    public HelloService create(Throwable throwable) {
        return new HelloService() {
            @Override
            public String hello() {
                return "HelloService调用失败："+throwable.getClass().getName();
            }
        };
    }
}
