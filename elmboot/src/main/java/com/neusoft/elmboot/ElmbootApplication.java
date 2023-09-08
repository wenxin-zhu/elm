package com.neusoft.elmboot;

import com.neusoft.elmboot.util.CommonUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElmbootApplication {

    // Spring Boot Application
    public static void main(String[] args) {
        System.out.println(CommonUtil.getCurrentDate() + " 程序运行");
        SpringApplication.run(ElmbootApplication.class, args);
    }
}
