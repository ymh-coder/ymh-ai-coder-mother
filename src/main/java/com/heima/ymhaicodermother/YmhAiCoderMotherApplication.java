package com.heima.ymhaicodermother;

import dev.langchain4j.community.store.embedding.redis.spring.RedisEmbeddingStoreAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {RedisEmbeddingStoreAutoConfiguration.class})
@MapperScan("com.heima.ymhaicodermother.mapper")
public class YmhAiCoderMotherApplication {

    public static void main(String[] args) {
        SpringApplication.run(YmhAiCoderMotherApplication.class, args);
    }

}
