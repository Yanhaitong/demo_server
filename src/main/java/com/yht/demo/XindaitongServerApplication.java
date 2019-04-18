package com.yht.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.yht.demo.mapper")
public class XindaitongServerApplication extends SpringBootServletInitializer {
    private static final Logger logger = LoggerFactory.getLogger(XindaitongServerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(XindaitongServerApplication.class, args);
        logger.info("========================启动完毕========================");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(XindaitongServerApplication.class);

    }

}
