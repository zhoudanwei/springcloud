package com.zdw.springcloud.hystric;

import com.zdw.springcloud.service.FeignService;
import org.apache.log4j.Logger;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Component
public class FeignServiceHystric implements FeignService{

    private  Logger logger = Logger.getLogger(FeignServiceHystric.class);

    @Override
    public String sayHi(String name) {
        logger.info("调用HelloService接口失败了,执行熔断器方法,传入参数:"+name);
        return "对不起,"+name+",调用失败了!";
    }
}