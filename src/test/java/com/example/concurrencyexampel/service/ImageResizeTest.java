package com.example.concurrencyexampel.service;

import com.example.concurrencyexampel.dto.ResizeResponse;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ImageResizeTest {

  @Autowired
  private FileService fileService;

  private static final Logger LOGGER = LoggerFactory.getLogger(ImageResizeTest.class);

  @Test
  public void test() throws InterruptedException {
    LOGGER.info("===TEST START===");
    var list = new ArrayList<CompletableFuture<ResizeResponse>>();

    for (var index = 0; index < 3; index++) {
      list.add(
          fileService.resize(
              "/{0}/{0}/{0}/{0}/{0}".replace("{0}", String.valueOf(index))));
    }

    LOGGER.info("======중간처리========");

    list.parallelStream()
        .map(it -> {
          try {
            return it.get();
          } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("리사이즈 실패...");
          }
        })
        .forEach((it) -> {
          LOGGER.info("+++++++++++++++업로드 처리+++++++++++++++++");
          LOGGER.info(it.getPath());
          LOGGER.info(it.getResizeList().toString());
        });

    LOGGER.info("==TEST END====");
  }
}
