package com.oskay.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/spring-boot")
    public String index() {
        return "Welcome to i2i Academy! Mustafa Oskay! Spirng Boot";
    }
}