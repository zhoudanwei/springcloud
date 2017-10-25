package com.zdw.springcloud;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 一个SpringCloud的Zipkin客户端(服务提供者)
 */
@SpringBootApplication
@RestController
public class ClientZipkinMiyaApplication {
    private Logger logger = Logger.getLogger(ClientZipkinMiyaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ClientZipkinMiyaApplication.class, args);
    }

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Value("${server.port}")
    String port;

    @RequestMapping("/hi/{name}")
    public String home(@PathVariable String name) {
        logger.info("Trace开始,调用Client-Zipkin客户端接口,传入参数:" + name);
        return restTemplate.getForObject("http://localhost:8989/miya", String.class);
    }

    @RequestMapping("/info")
    public String info() {
        logger.log(Level.INFO, "调用 Trace,来自: Client-Zipkin");

        return "我是 Client-Miya";

    }

    @Bean
    public AlwaysSampler defaultSampler() {
        return new AlwaysSampler();
    }
}
