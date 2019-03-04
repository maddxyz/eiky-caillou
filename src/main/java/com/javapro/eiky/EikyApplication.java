package com.javapro.eiky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.javapro.eiky.Models")
@ComponentScan
@SpringBootApplication
@EnableJpaRepositories("com.javapro.eiky.Models")
public class EikyApplication {

    public static void main(String[] args) {
        SpringApplication.run(EikyApplication.class, args);
    }

}
