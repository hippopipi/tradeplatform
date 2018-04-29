package com.zhenglin.tradeplatform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.zhenglin.tradeplatform.mapper")
public class TPServer {

    public static void main(String[] args) {
        SpringApplication.run(TPServer.class, args);
    }
}
