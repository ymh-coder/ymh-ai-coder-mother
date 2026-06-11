package com.heima.ymhaicodermother;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class YmhAiCodeScreenshotApplication {
    public static void main(String[] args) {
        SpringApplication.run(YmhAiCodeScreenshotApplication.class, args);
    }
}
