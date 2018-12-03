package com.github.m5.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class M5AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(M5AdminApplication.class, args);
    }
}
