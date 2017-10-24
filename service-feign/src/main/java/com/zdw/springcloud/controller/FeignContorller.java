package com.zdw.springcloud.controller;

import com.zdw.springcloud.service.FeignService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignContorller {

    private  Logger logger = Logger.getLogger(FeignContorller.class);

    @Autowired
    private FeignService feignService;

    @RequestMapping(value = "/hi/{name}",method = RequestMethod.GET)
    public String hi(@PathVariable String name){
        logger.info("进入Feign服务的HelloController,准备调用HelloService接口,传入参数:"+name);
        return feignService.sayHi(name);
    }
}
