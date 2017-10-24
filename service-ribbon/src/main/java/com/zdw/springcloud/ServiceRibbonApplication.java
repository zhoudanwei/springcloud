package com.zdw.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 一个SpringCloud的负载均衡(服务消费者)
 * 在程序的启动类加@EnableHystrix注解开启Hystrix(断路器,避免雪崩)
 * Netflix开源了Hystrix组件，实现了断路器模式，SpringCloud对这一组件进行了整合。在微服务架构中，一个请求需要调用多个服务是非常常见的
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableHystrix
//(以下开启Hystrix Dashboard (断路器监控中心))
@EnableHystrixDashboard
public class ServiceRibbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRibbonApplication.class, args);
	}

	@Bean
	@LoadBalanced
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
