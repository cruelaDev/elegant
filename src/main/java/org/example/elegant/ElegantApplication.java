package org.example.elegant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ElegantApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElegantApplication.class, args);
    }
}
