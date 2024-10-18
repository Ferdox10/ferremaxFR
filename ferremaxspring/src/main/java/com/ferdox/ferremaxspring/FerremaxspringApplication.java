package com.ferdox.ferremaxspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class FerremaxspringApplication {

    public static void main(String[] args) {
        SpringApplication.run(FerremaxspringApplication.class, args);
    }
    @GetMapping("/")
    public String home() {
        return "index"; // Prueba
    }
}
