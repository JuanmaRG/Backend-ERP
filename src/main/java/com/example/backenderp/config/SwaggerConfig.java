package com.example.backenderp.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerConfiguration(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("package com.example.backenderp.controller;"))
                .build().apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo("Backend - Proyecto Final Curso Java",
                "This is the documentation of Juanma`s practise 4 project",
                "1.0",
                "",
                new Contact("Juanma",""," juanma@gmail.com"),
                "",
                "",
                Collections.emptyList());
    }

}
