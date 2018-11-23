package com.github.m5.cloud.config.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * @author xiaoyu
 */
@RestControllerAdvice
public class MyExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(Exception.class)
    public String handler(Exception e) {
        logger.error(e.getMessage(), e);
        return "系统异常";
    }

    @RestController
    public class GloalErrorController implements ErrorController {

        @RequestMapping("/error")
        public String error(HttpServletResponse response) {
            if (HttpStatus.NOT_FOUND.value() == response.getStatus()) {
                return "接口不存在";
            }
            return "系统错误";
        }

        @Override
        public String getErrorPath() {
            return new ErrorProperties().getPath();
        }
    }
}
