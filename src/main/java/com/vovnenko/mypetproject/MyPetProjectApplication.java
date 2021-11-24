package com.vovnenko.mypetproject;

import com.vovnenko.mypetproject.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfig.class)

public class MyPetProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyPetProjectApplication.class, args);
    }

}
