package com.m5.cloud.greeting.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaoyu
 */
@RestController
public class GreetingServiceImpl implements GreetingService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String sayHi(String name) {
        logger.info("----sayHi----, {}", name);
        return "hi " + name;
    }

    @Override
    public String sayHello(String name) {
        logger.info("----sayHello----, {}", name);
        return "hello " + name;
    }
}
