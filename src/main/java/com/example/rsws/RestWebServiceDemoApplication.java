package com.example.rsws;

import com.example.rsws.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example.rsws" }, basePackageClasses = {RestWebServiceDemoApplication.class, SwaggerConfig.class})
public class RestWebServiceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestWebServiceDemoApplication.class, args);
    }

}