package com.spring.dev.controller;

import com.spring.dev.entities.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class GreetingController {

    @Autowired
    Greeting greeting;

    AtomicInteger atomicInteger = new AtomicInteger();
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name") String name){
        greeting.setId(atomicInteger.incrementAndGet());
        greeting.setContent("Hey "+name+ ", Welcome to Pune");
        return greeting;
    }
}
