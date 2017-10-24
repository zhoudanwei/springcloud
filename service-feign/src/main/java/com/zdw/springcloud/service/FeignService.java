package com.zdw.springcloud.service;

import com.zdw.springcloud.hystric.FeignServiceHystric;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value="spring-cloud-client",fallback = FeignServiceHystric.class)
public interface FeignService {
    @RequestMapping(value = "/hi/{name}",method = RequestMethod.GET)
    String sayHi(@PathVariable(name = "name") String name);
}