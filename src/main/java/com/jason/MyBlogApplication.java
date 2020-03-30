package com.jason;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@ServletComponentScan
@MapperScan(basePackages={"com.jason.dao"})
@SpringBootApplication
public class MyBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBlogApplication.class, args);
    }

}
