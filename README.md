# 微服务笔记

## 一、注册中心

### 1、是什么？

​	注册中心可以说是微服务架构中的”通讯录“，它记录了服务和服务地址的映射关系。在分布式架构中，服务会注册到这里，当服务需要调用其它服务时，就这里找到服务的地址，进行调用。

![r](https://image-static.segmentfault.com/350/840/3508407436-5b7c045fba333_articlex)



### 2、有什么用？

​	在分布式系统中，我们不仅仅是需要在注册中心找到服务和服务地址的映射关系这么简单，我们还需要考虑更多更复杂的问题：

1. <u>服务注册后，如何被及时发现</u>
2. <u>服务宕机后，如何及时下线</u>
3. <u>服务如何有效的水平扩展</u>
4. <u>服务发现时，如何进行路由</u>
5. <u>服务异常时，如何进行降级</u>
6. <u>注册中心如何实现自身的高可用</u>

这里问题的解决都依赖于注册中心。简单看，注册中心的功能有点类似于DNS服务器或者负载均衡器，而实际上，注册中心作为微服务的基础组件，可能要更加复杂，也需要更多的灵活性和时效性。



### 3、有哪些？

- Spring Cloud Netflix Eureka
- Spring Cloud Zookeeper
- Spring Cloud Consul
- Spring Cloud Alibaba Nacos



### 4、怎么用？

​	这里以nacos为例：官方文档：<https://nacos.io/zh-cn/docs/what-is-nacos.html>

​	1）、引入依赖：	

```xml
		<dependency>
			<groupId>com.alibaba.cloud</groupId>
			<artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
		</dependency>
```

​	2)、添加注解：

```java
@SpringBootApplication
//启动注册中心客服端功能
@EnableDiscoveryClient
public class ServiceProvideApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceProvideApplication.class, args);
	}
}
```

​	3）、配置文件

```yaml
server:
  port: 8081
spring:
  application:
  //服务名称
    name: service-provider
  cloud:
    nacos:
      discovery:
      //注册中心地址
        server-addr: 192.168.0.21:8848
```





## 二、服务调用

### 1、介绍

​	微服务架构中，业务都会被拆分成一个独立的服务，服务与服务的通讯是基于http restful的。Spring cloud有两种服务调用方式，一种是ribbon+restTemplate，另一种是feign。接下来分别对这两种的进行讲解。服务的调用还是在上一节服务的注册和发现的基础上进行的。


### 2、RestTemplate

```java
	@Bean
	//开启负载均衡功能
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
```

```java
@RestController
public class HelloController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/hello")
    public String hello(){
        return restTemplate.getForObject("http://service-provider/hello", String.class);
    }
}
```



### 3、OpenFeign

### 简介：

| 名称                     | 说明                                       | 官方文档                                     |
| ---------------------- | ---------------------------------------- | ---------------------------------------- |
| Feign                  | Feign是Netflix开发的声明式、模板化的HTTP客户端， Feign可以帮助我们更快捷、优雅地调用HTTP API | https://github.com/OpenFeign/feign       |
| Hystrix                | Hystrix是Netflix开源的一款容错框架，同样具有自我保护能力。为了实现容错和自我保护 | <https://github.com/Netflix/hystrix>     |
| Ribbon                 | Netflix发布的开源项目，主要功能是提供客户端的软件负载均衡算法       | https://github.com/Netflix/ribbon        |
| Spring Cloud OpenFeign | 是基于Netflix feign实现，使Feign支持了Spring MVC注解，从而让Feign的使用更加方便,还整合了Spring Cloud Ribbon和Spring Cloud Hystrix | <https://cloud.spring.io/spring-cloud-static/spring-cloud-openfeign/2.1.3.RELEASE/single/spring-cloud-openfeign.html> |



### 使用

1）、引入依赖：

```xml
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-openfeign</artifactId>
		</dependency>
```

2）、添加注解：

```java
@SpringBootApplication
//开启Feign客户端功能
@EnableFeignClients
public class ServiceConsumerFeignApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceConsumerFeignApplication.class, args);
	}
}
```

2)、编写服务调用接口

```java
@FeignClient(value = "service-provider")
public interface HelloService {
    @RequestMapping("/hello")
    String hello();
}
```



























### 文档列表：

​	springboot最新官方文档: <https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/reference/html/>

​	springboot中文文档：<https://docshome.gitbooks.io/springboot/content/>（旧版本）

​	springcloud官方：<https://cloud.spring.io/spring-cloud-static/Greenwich.SR3/single/spring-cloud.html>

​	springcloud中文文档：<https://www.springcloud.cc/spring-cloud-dalston.html>（旧版本）

​	spring-cloud-alibaba: <https://github.com/alibaba/spring-cloud-alibaba/blob/master/README-zh.md>

​	