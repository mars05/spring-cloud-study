package com.github.m5.cloud.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author xiaoyu
 */
@EnableConfigServer
@SpringBootApplication
public class M5CloudConfigServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(M5CloudConfigServerApplication.class, args);
    }
}
