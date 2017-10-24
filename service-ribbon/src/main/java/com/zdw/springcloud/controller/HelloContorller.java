package com.zdw.springcloud.controller;

import com.zdw.springcloud.service.HelloService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloContorller {

    private  Logger logger = Logger.getLogger(HelloContorller.class);

    @Autowired
    private HelloService helloService;

    @RequestMapping("/hi/{name}")
    public String hi(@PathVariable String name){
        logger.info("进入Ribbon服务的HelloController,准备调用HelloService接口,传入参数:"+name);
        return helloService.hiService(name);
    }
}
