package com.heima.ymhaicodeuser;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDubbo
@MapperScan("com.heima.ymhaicodeuser.mapper")
@ComponentScan("com.heima")
public class YmhAiCodeUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(YmhAiCodeUserApplication.class, args);
    }
}
