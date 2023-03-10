package com.example.catalogfilm.service.impl;

import com.example.catalogfilm.service.CounterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CounterServiceImpl implements CounterService{
    private static int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getValue() {
        return count;
    }
}


