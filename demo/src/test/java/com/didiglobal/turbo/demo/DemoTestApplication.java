package com.didiglobal.turbo.demo;

import com.didiglobal.turbo.engine.annotation.EnableTurboEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableTurboEngine
@SpringBootApplication(scanBasePackages = {"com.didiglobal.turbo.demo"})
public class DemoTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoTestApplication.class, args);
    }

}
