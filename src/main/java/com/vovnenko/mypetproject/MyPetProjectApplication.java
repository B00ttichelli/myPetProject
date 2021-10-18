package com.vovnenko.mypetproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MyPetProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyPetProjectApplication.class, args);
    }

}
