package com.example.releases.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController


public class UserController {

    @PostMapping("/process_signup")
    public String processSignup(){
        return null;
    }

    @GetMapping("/private/hello")
    public String hello(){
        return "hello";
    }


}
