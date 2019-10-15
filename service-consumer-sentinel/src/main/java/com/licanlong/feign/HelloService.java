package com.licanlong.feign;

import com.licanlong.feign.fallback.HelloServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author licl
 * @date 2019/8/31
 */
@FeignClient(value = "service-provider",fallback = HelloServiceFallback.class)
public interface HelloService {
    @RequestMapping("/hello")
    String hello();
}
