package com.ang.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: ssang
 * @create: 2021/6/24 0024 13:32
 **/
@SpringBootApplication
public class SwaggerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SwaggerApplication.class);
        System.out.println("Swagger访问地址：http://localhost:8092/swagger-ui/index.html");
    }
}
