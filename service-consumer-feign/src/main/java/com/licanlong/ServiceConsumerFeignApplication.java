package com.licanlong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServiceConsumerFeignApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceConsumerFeignApplication.class, args);
	}
}
