package com.example.catalogfilm.controller;


import com.example.catalogfilm.service.AsyncService;
import com.example.catalogfilm.service.CounterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/counter")
@RequiredArgsConstructor
public class CounterController {

    private final CounterService counterService;

    private final AsyncService asyncService;

    @GetMapping("/async")
    public String async() {
        for (int i = 0; i < 3; ++i){
            asyncService.asyncIncrement();
        }
        return "The value was incremented on 3000000";
    }

    @GetMapping("/increment")
    public void increment() {
        counterService.increment();
    }

    @GetMapping("/value")
    public int getValue() {
        return counterService.getValue();
    }
}