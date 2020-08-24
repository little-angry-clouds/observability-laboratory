package com.observabilityLaboratory.spring.springboot.helloworld.controller;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

  private AtomicInteger gauge;

  public HelloWorldController(MeterRegistry registry) {
    this.gauge = registry.gauge("java_microservice_wait_time",
                                Tags.of("service", "java-microservice"),
                                new AtomicInteger(0));
  }

  @RequestMapping(value = "/", produces = {"application/json"})
  public JSONObject doHelloWorld(HttpServletRequest request) throws Exception {
    Random random = new Random();
    JSONObject response = new JSONObject();
    int wait_time = random.ints(1, 6).findFirst().getAsInt();
    response.put("message", "Hello World!");
    Thread.sleep(wait_time * 1000);
    this.gauge.set(wait_time);

    return response;
  }
}
