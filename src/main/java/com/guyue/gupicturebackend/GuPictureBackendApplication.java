package com.guyue.gupicturebackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.guyue.gupicturebackend.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class GuPictureBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuPictureBackendApplication.class, args);
    }

}
