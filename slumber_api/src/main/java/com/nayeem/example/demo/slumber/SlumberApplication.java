package com.nayeem.example.demo.slumber;

import com.nayeem.example.demo.slumber.configuration.settings.JwtSettings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({JwtSettings.class})
public class SlumberApplication {

    public static void main(String[] args) {
        SpringApplication.run(SlumberApplication.class, args);
    }

}
