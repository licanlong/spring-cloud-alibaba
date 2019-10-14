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



​		















































### 文档列表：

​	springboot最新官方文档: <https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/reference/html/>

​	springboot中文文档：<https://docshome.gitbooks.io/springboot/content/>（旧版本）

​	springcloud官方：<https://cloud.spring.io/spring-cloud-static/Greenwich.SR3/single/spring-cloud.html>

​	springcloud中文文档：<https://www.springcloud.cc/spring-cloud-dalston.html>（旧版本）

​	spring-cloud-alibaba: <https://github.com/alibaba/spring-cloud-alibaba/blob/master/README-zh.md>

​	