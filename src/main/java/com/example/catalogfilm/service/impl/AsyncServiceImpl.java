package com.example.catalogfilm.service.impl;

import com.example.catalogfilm.service.AsyncService;
import com.example.catalogfilm.service.FlowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AsyncServiceImpl implements AsyncService {

    private final FlowService flowService;

    @Override
    public void createFlow() {
        for (int i = 0; i < 3; ++i){
            flowService.callIncrement();
        }
    }
}
