package com.yourcompany.vehiclepayment;  // Fixed package name (removed underscore)

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import com.yourcompany.vehiclepayment.config.DatabaseConfig;  // Fixed import path

@SpringBootApplication
public class VehiclePaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehiclePaymentApplication.class, args);
    }

    @Bean
    public CommandLineRunner initializeDatabase() {
        return args -> {
            DatabaseConfig.initializeDatabase();
            System.out.println("Database initialization completed!");
        };
    }
}