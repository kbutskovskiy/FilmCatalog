package com.example.catalogfilm.service.impl;

import com.example.catalogfilm.service.AsyncService;
import com.example.catalogfilm.service.CounterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AsyncServiceImpl implements AsyncService {

    private final CounterService counterService;

    @Async
    @Override
    public synchronized void asyncIncrement() {
        log.info("Async endpoint started by me");
        for (int i = 0; i < 1000000; ++i) {
            counterService.increment();
        }
        log.info("Async endpoint ended");
    }
}
