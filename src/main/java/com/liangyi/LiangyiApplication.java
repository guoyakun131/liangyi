package com.liangyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
public class LiangyiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiangyiApplication.class, args);
    }

}