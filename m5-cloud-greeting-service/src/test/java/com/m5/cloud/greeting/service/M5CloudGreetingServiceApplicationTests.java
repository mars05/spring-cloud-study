package com.m5.cloud.greeting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class M5CloudGreetingServiceApplicationTests {

    @Autowired
    private GreetingService greetingService;

    public static void main(String[] args) throws Exception {
        GreetingService greetingService = SpringApplication.run(M5CloudGreetingServiceApplicationTests.class, args).getBean(GreetingService.class);
        for (int i = 0; i < 10000; i++) {
            try {
                System.out.println(greetingService.sayHello("张三"));
//                System.out.println(greetingService.sayHi("李四"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            Thread.sleep(1000);
        }
    }

}

