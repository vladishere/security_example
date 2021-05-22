package com.example.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
    @ResponseBody
    @GetMapping("/hello")
    public String test() {
        return "Hello World";
    }
}
