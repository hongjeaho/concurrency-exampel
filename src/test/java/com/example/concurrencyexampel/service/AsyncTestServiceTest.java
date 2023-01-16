package com.example.concurrencyexampel.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest
class AsyncTestServiceTest {

    @Autowired
    private AsyncTestService asyncTestService;

    @Test
    public void test() throws InterruptedException {

        System.out.println("===TEST START===");
        var list = new ArrayList<CompletableFuture<String>>();

        for (var index = 0; index < 3; index++) {
            list.add(asyncTestService.resize("/{0}/{0}/{0}/{0}/{0}".replace("{0}", String.valueOf(index))));
        }

        System.out.println("======중간정산...========");


        list.forEach(item -> {
            try {
                System.out.println(item.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });


        System.out.println("==TEST END====");
    }
}