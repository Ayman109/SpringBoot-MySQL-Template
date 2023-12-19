package com.example.springboot_mysql_template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootMongoTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMongoTemplateApplication.class, args);
        System.out.println("server run in : http://localhost:8085");

    }

}
