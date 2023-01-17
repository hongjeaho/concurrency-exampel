package com.example.concurrencyexampel.service;

import com.example.concurrencyexampel.dto.ResizeResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FileService {

  private static final List<Integer> DEFAULT_IMAGE_SIZE_LIST = List.of(1000, 400, 200);
  private static final Map<String, List<Integer>> IMAGE_SIZE_MAP = Map.of(
      "PET_IMAGE", List.of(1000, 200)
  );

  @Async
  public CompletableFuture<ResizeResponse> resize(final String originPath)
      throws InterruptedException {

    //파일 다운로드
    var filePath = generatePath(originPath);

    log.info("########[async start]###############");
    var resizeList = new ArrayList<String>();

    log.info("#########{} : {}###########", originPath, filePath);

    if (originPath.contains("1")) {
      // throw new RuntimeException("Exception Example");
    }

    DEFAULT_IMAGE_SIZE_LIST
        .forEach(size -> resizeList.add(filePath + "_" + size));

    Thread.sleep(1000);
    log.info("##########async end#############");

    return CompletableFuture.completedFuture(ResizeResponse.builder()
        .path(filePath)
        .resizeList(resizeList)
        .build());
  }

  private String generatePath(final String name) {
    return UUID.randomUUID().toString();
  }
}
