package com.liangyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableTransactionManagement
public class LiangyiApplication {

    private int a;
    public static void main(String[] args) {
        SpringApplication.run(LiangyiApplication.class, args);
    }

}