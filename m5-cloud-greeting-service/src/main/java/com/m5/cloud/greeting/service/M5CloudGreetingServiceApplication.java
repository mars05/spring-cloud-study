package com.m5.cloud.greeting.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class M5CloudGreetingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(M5CloudGreetingServiceApplication.class, args);
    }

}

