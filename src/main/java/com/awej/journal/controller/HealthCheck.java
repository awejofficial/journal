package com.awej.journal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    @GetMapping("/ok")
    public String checkHealth(){
        return "ok";
    }

}
