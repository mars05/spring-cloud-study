package com.m5.cloud.greeting.service;

import org.springframework.stereotype.Component;

/**
 * @author xiaoyu
 */
@Component("GreetingServiceHystrix")
public class GreetingServiceHystrix implements GreetingService {
    @Override
    public String sayHi(String name) {
        return "sayHi 调用失败";
    }

    @Override
    public String sayHello(String name) {
        return "sayHello 调用失败";
    }
}
