package com.vovnenko.mypetproject.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().apiInfo(getInfo());
    }

    private ApiInfo getInfo(){
        return new ApiInfoBuilder()
                .title("OldSchool Styled Forum")
                .version("0.1 Alpha")
                .description("Api for Forum")
                .contact(new Contact("Serhii Vovnenko","",""))
                .license("GPL")
                .build();
    }

}
