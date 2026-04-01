package com.heima.ymhaicodermother;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.heima.ymhaicodermother.mapper")
public class YmhAiCoderMotherApplication {

    public static void main(String[] args) {
        SpringApplication.run(YmhAiCoderMotherApplication.class, args);
    }

}
