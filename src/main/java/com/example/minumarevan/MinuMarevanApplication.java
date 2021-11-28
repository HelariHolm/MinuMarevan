package com.example.minumarevan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.servlet.annotation.MultipartConfig;

@SpringBootApplication
public class MinuMarevanApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinuMarevanApplication.class, args);
    }

}
