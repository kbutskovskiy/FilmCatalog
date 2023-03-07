package com.example.catalogfilm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class SecurityController {

    @GetMapping("/helloUser")
    public String helloUser() {
        return "helloUser";
    }

    @GetMapping("/helloAdmin")
    public String helloAdmin() {
        return "helloAdmin";
    }

    @GetMapping("/helloAny")
    public String helloAny() {
        return "helloAny";
    }
}
