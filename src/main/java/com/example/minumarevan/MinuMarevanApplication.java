package com.example.minumarevan;

import com.example.minumarevan.repository.AnalysisRepository;
import com.example.minumarevan.repository.ContactNumbersRepository;
import com.example.minumarevan.user.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.servlet.annotation.MultipartConfig;

@SpringBootApplication
//@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@EnableJpaRepositories(basePackageClasses={
        UserRepository.class,
        ContactNumbersRepository.class,
        AnalysisRepository.class
})
public class MinuMarevanApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinuMarevanApplication.class, args);
    }

}
