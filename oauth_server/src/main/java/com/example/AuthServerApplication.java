package com.example;

import com.example.config.RsaKeyProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author john
 * @date 2020/1/12 - 10:07
 */
@SpringBootApplication
@MapperScan("com.example.mapper")
@EnableConfigurationProperties(RsaKeyProperties.class)
public class AuthServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }
}
