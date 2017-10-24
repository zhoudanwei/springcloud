package com.zdw.springcloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zdw.springcloud.service.HelloService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class HelloServiceImpl implements HelloService{

    private  Logger logger = Logger.getLogger(HelloServiceImpl.class);

    @Autowired
    RestTemplate restTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "hiError")//熔断器
    public String hiService(String name){
        logger.info("调用HelloService接口,使用RestTemplate进行远程调用,传入参数:"+name);
        return restTemplate.getForObject("http://SPRING-CLOUD-CLIENT/hi/"+name,String.class);
    }

    /**
     * 熔断后执行的方法
     * @param name
     * @return
     */
    public String hiError(String name) {
        logger.info("调用HelloService接口失败了,执行熔断器方法,传入参数:"+name);
        return "对不起,"+name+",调用失败了!";
    }
}
