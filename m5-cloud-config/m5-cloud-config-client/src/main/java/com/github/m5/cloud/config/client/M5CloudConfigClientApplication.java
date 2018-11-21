package com.github.m5.cloud.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiaoyu
 */
@EnableSwagger2
@RestController
@SpringBootApplication
public class M5CloudConfigClientApplication {
    private long lastModified = System.currentTimeMillis();
    @Value("${age}")
    String name = "World";
    AtomicInteger i = new AtomicInteger();

    private RestTemplate restTemplate = new RestTemplate();

    @RequestMapping("/")
    public ResponseEntity home() {
        String result = "hell " + name + " " + i.incrementAndGet();
        System.out.println("哈哈哈");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLastModified(lastModified);
//        httpHeaders.setETag("\"" + DigestUtils.md5DigestAsHex(result.getBytes()) + "\"");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(result);
    }

    @RequestMapping("/a")
    @ResponseBody
    public ModelAndView a() {
        Map map = new HashMap();
        map.put("abc", 123123);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addAllObjects(map).setViewName("redirect:/404.htm");
        return modelAndView;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean123() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new Filter() {
            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
                System.out.println("doFilter");
                filterChain.doFilter(servletRequest, servletResponse);
                System.out.println("====doFilter====");

            }
        });
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()  // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.any()) // 对所有api进行监控
                .paths(PathSelectors.any()) // 对所有路径进行监控
                .build();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(M5CloudConfigClientApplication.class, args);
        System.out.println(run);
        System.out.println(run.getParent());
    }

}
