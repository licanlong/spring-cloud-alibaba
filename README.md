# 微服务笔记

### 技术栈介绍：

​	SpringCould就是对微服务的一个具体规范实现； 微服务基于SpringCloud来实现， 而SpringCloud是基于SpringBoot开发的；对于SpringCloud 的这套规范，有两个具体实现就是SCA和SCN。



**SCN**： 即Spring  Cloud  Netflix 的简称。Spring  Cloud  Netflix是SpringCloud 规范的第一套实现，在微服务刚流行的时候很多公司选型用的都是SpingCloud下的SpringCloud Netflix全家桶来进行搭建微服务项目架构。也就是大家熟悉的SpringCloud五大神兽。可是现在五大神兽出现了一些变化，所以才会有人开始引用SCA中的一些组建来代替SCN中的五大神兽。

五大神兽：

​	服务发现——Netflix Eureka  : 服务的注册与发现，服务提供者将服务注册到注册中心，服务消费者通过服务信息来消费

​	客服端负载均衡——Netflix Ribbon 负载均衡

​	断路器——Netflix Hystrix : 对服务做限流、降级、隔断来做处理

​	服务网关——Netflix Zuul ： 服务网关， 所用的请求都通过网关进行发送，进行一些过滤审计。

​	分布式配置——Spring Cloud Config 自动化配置



**SCA**：  即Spring Cloud Alibaba 的简称。 SpringCloud Alibaba是阿里对SpringCloud不同于SpringCloud Netflix 的另一套实现。他是中国版的微服务架构实现，对于中国的程序员来说应该是一个福利。他对SpringCloud的中的一些组件进行替换，来实现微服务的架构设计。SpringCloud Alibaba 经过一年在SpringCloud的孵化 ，在2019-7底第一版毕业。 看到官网上说后面还会有很多的更新和新增，到时候会把dubbo和SpringCloud无缝整合等，如果感心趣的话可以关注SCA的发展，以后他可能就是微服务的趋势。

SCA中的组件：

​	Sentinel：把流量作为切入点，从流量控制、熔断降级、系统负载保护等多个维度保护服务的稳定性。

​	Nacos：一个更易于构建云原生应用的动态服务发现、配置管理和服务管理平台。

​	RocketMQ：一款开源的分布式消息系统，基于高可用分布式集群技术，提供低延时的、高可靠的消息发布与订阅服务。

​	Alibaba Cloud ACM：一款在分布式架构环境中对应用配置进行集中管理和推送的应用配置中心产品。

​	Alibaba Cloud OSS: 阿里云对象存储服务（Object Storage Service，简称 OSS），是阿里云提供的海量、安全、低成本、高可靠的云存储服务。您可以在任何应用、任何时间、任何地点存储和访问任意类型的数据。

​	Alibaba Cloud SchedulerX: 阿里中间件团队开发的一款分布式任务调度产品，提供秒级、精准、高可靠、高可用的定时（基于 Cron 表达式）任务调度服务。



**主要文档**：

​	springboot官方文档: <https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/reference/html/>

​	springboot中文文档：<https://docshome.gitbooks.io/springboot/content/>

​	springcloud官方：<https://cloud.spring.io/spring-cloud-static/Greenwich.SR3/single/spring-cloud.html>

​	springcloud中文文档：<https://www.springcloud.cc/spring-cloud-dalston.html>

​	spring-cloud-alibaba: <https://github.com/alibaba/spring-cloud-alibaba/blob/master/README-zh.md>



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

​	1)、引入依赖：	

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

​	3)、配置文件

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

​	微服务架构中，业务都会被拆分成一个个独立的服务，服务与服务的通讯是基于http restful的。Spring cloud有两种服务调用方式，一种是ribbon+restTemplate，另一种是feign。接下来分别对这两种的进行讲解。服务的调用还是在上一节服务的注册和发现的基础上进行的。

### 2、RestTemplate

​	1）什么是RestTemplate

​		传统情况下在java代码里访问restful服务，一般使用`Apache`的`HttpClient`。不过此种方法使用起来太过繁琐。spring提供了一种简单便捷的模板类来进行操作，这就是`RestTemplate`。

​		REST服务与RestfulAPI风格：<https://blog.csdn.net/weixin_30765505/article/details/97360736>

​		

​	2）SpringCloud 中 RestTemplate 的使用

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
| Hystrix                | Hystrix是Netflix开源的一款容错框架，具有自我保护能力，为了实现容错和自我保护 | <https://github.com/Netflix/hystrix>     |
| Ribbon                 | Netflix发布的开源项目，主要功能是提供客户端的软件负载均衡算法       | https://github.com/Netflix/ribbon        |
| Spring Cloud OpenFeign | 是基于Netflix feign实现，使Feign支持了Spring MVC注解，从而让Feign的使用更加方便,还整合了Spring Cloud Ribbon和Spring Cloud Hystrix | <https://cloud.spring.io/spring-cloud-static/spring-cloud-openfeign/2.1.3.RELEASE/single/spring-cloud-openfeign.html> |



### 使用：

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

3)、编写服务调用接口

```java
@FeignClient(value = "service-provider",fallback= HelloServiceFallback.class)
public interface HelloService {
    @RequestMapping("/hello")
    String hello();
}
```

4)、使用Hystrix：

​	1、开启Hystrix:

```yaml
feign:
  hystrix:
    enabled: true
```

​	2、编写调用失败回调逻辑Fallback	

```java
@Component
public class HelloServiceFallback implements HelloService{
    @Override
    public String hello() {
        return "HelloService调用失败";
    }
}

//这种写法可以得到具体的异常
@Component
public class HelloServiceFallbackFactory implements FallbackFactory<HelloService>{
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

```

5）Ribbon的负载均衡策略

​	默认情况下Ribbon使用RoundRobinRule（轮询）如果需要修改策略，则需要向spring ioc 容器 注入IRule接口的实现类，如：

```java
	@Bean
	public IRule nacosRule(){
		return new NacosRule();
	}
```























​	