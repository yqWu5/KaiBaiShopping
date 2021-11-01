package com.kaibai.shopping;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kaibai.shopping.mapper")
public class KaiBaiShoppingApplication {
    public static void main(String[] args) {
        SpringApplication.run(KaiBaiShoppingApplication.class, args);
    }
}
