package com.example.rsws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

//This file is required to enable Swagger functionality in Rest WebService
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    Contact contact = new Contact(
            "Haneesh Singhal",
            "NoURL",
            "haneesh_singhal@yahoo.com"
    );
    List<VendorExtension> vendorExtensions = new ArrayList<>();

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.rsws.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }
    private ApiInfo metaData() {
        return new ApiInfo(
                "Spring Boot REST API",
                "Spring Boot REST API for Student",
                "1.0",
                "xyz.com",
                contact,
                "Open Source License",
                "abc.com",
                vendorExtensions);
    }
}