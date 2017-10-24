package com.zdw.springcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SpringCloud的配置中心客户端
 * 加入Spring Cloud Bus:
 * (将分布式的节点用轻量的消息代理连接起来。
 * 用于广播配置文件的更改或者服务之间的通讯，也可以用于监控。
 * 本文要讲述的是实现通知微服务架构的配置文件的更改。)
 * 不关服务器刷新请求路径:http://localhost:8881/bus/refresh
 * 使用destination参数：
 * 比如 “/bus/refresh?destination=customers:**” 即刷新服务名为customers的所有服务,不管ip。
 * 当git文件更改的时候，通过pc端用post 向端口为8881的config-client发送请求/bus/refresh／
 * 此时8881端口会发送一个消息，由消息总线向其他服务传递，从而使整个微服务集群都达到更新配置文件。
 * 另一个作用是:可以用作自定义的Message Broker,只需要bus-amqp包, 然后再配置文件写上配置即可;
 * Tracing Bus Events：需要设置：spring.cloud.bus.trace.enabled=true.
 * 如果那样做的话，那么Spring Boot TraceRepository（如果存在）将显示每个服务实例发送的所有事件和所有的ack报文
 */
@SpringBootApplication
//开启配置服务器
@RestController
//注册服务到服务注册中心
@EnableEurekaClient
public class ConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}

	@Value("${foo}")
	String foo;
	@RequestMapping(value = "/hi")
	public String hi(){
		return foo;
	}
}