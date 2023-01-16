package com.example.concurrencyexampel.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Slf4j
@Service
public class AsyncTestService {

    @Async
    public CompletableFuture<String> resize(@NonNull final String path) throws InterruptedException {

        log.info("########[async start]###############");
        Thread.sleep(1000);
        log.info("#########{}###########", path);
        log.info("##########async end#############");


        return CompletableFuture.completedFuture(UUID.randomUUID().toString()) ;
    }
}
