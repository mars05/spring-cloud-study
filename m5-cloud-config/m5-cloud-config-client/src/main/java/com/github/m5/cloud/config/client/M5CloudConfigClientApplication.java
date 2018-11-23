package com.github.m5.cloud.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiaoyu
 */
@RestController
@SpringBootApplication
public class M5CloudConfigClientApplication {
    private long lastModified = System.currentTimeMillis();
    @Value("${username}")
    String name;
    AtomicInteger i = new AtomicInteger();

    @RequestMapping("/abc")
    public String abc() {
        return "abc";
    }

    @GetMapping("/")
    public ResponseEntity home() {
        String result = "hell " + name + " " + i.incrementAndGet();
        System.out.println("哈哈哈");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLastModified(lastModified);
        int i = 1 / 0;
//        httpHeaders.setETag("\"" + DigestUtils.md5DigestAsHex(result.getBytes()) + "\"");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(result);
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean123() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter((servletRequest, servletResponse, filterChain) -> {
            System.out.println("doFilter before");
            filterChain.doFilter(servletRequest, servletResponse);
            System.out.println("doFilter after");
        });
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }


    public static void main(String[] args) {
        SpringApplication.run(M5CloudConfigClientApplication.class, args);
    }
}

