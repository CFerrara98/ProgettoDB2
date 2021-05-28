package com.example.progettodb22;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.example.progettodb22")
@SpringBootApplication(scanBasePackages={"com.example.progettodb22", "com.example.progettodb22"})
public class ProgettoDb2Application {
    public static void main(String[] args) {

        SpringApplication.run(ProgettoDb2Application.class, args);
    }


}
