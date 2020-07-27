package com.observabilityLaboratory.spring.springboot.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

  public Application() {
    // For Spring
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
