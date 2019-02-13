package com.m5.cloud.greeting.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * GreetingService
 */
@FeignClient(value = "greeting-service", fallback = GreetingServiceHystrix.class)
public interface GreetingService {

    @RequestMapping("sayHi")
    String sayHi(@RequestParam("name") String name);

    @RequestMapping("sayHello")
    String sayHello(@RequestParam("name") String name);

}
