package com.m5.cloud.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class M5CloudRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(M5CloudRegistryApplication.class, args);
    }

}

