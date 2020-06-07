package com.goods.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 * Time    : 2020/6/2 9:41 上午
 * Author  : mike.liu
 * File    : MainApplication.java
 */

@SpringBootApplication
@MapperScan(basePackages = "com.goods.model")
public class MainApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder);
    }
    //Spring Boot应用程序启动入口类
    public static void main(String[] args){
        SpringApplication.run(MainApplication.class, args);
    }
}


