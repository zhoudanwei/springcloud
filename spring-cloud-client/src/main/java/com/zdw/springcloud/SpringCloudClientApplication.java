package com.zdw.springcloud;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 一个SpringCloud的Eureka客户端(服务提供者)
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class SpringCloudClientApplication {
    private Logger logger = Logger.getLogger(SpringCloudClientApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudClientApplication.class, args);
    }

    @Value("${server.port}")
    String port;

    @RequestMapping("/hi/{name}")
    public String home(@PathVariable String name) {
        logger.info("调用Client客户端接口,传入参数:"+name);
        return "欢迎你，" + name + ",我来自端口:" + port;
    }
}
