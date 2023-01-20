package com.example.JavaProject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableSwagger2
public class JazApplication {
    public static void main(String[] args) {
        SpringApplication.run(JazApplication.class, args);
    }



//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.ant("*/accounts/*"))
//                .build();
//    }
}
