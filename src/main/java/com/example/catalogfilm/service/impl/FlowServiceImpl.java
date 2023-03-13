package com.example.catalogfilm.service.impl;

import com.example.catalogfilm.service.CounterService;
import com.example.catalogfilm.service.FlowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlowServiceImpl implements FlowService {

    private final CounterService counterService;

    @Async
    @Override
    public void callIncrement() {
        log.info("Async endpoint created by me");
        for (int i = 0; i < 1000000; ++i){
            counterService.increment();
        }
        log.info("END Async endpoint");
    }
}
