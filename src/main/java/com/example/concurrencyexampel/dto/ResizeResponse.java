package com.example.concurrencyexampel.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class ResizeResponse {

  private String path;
  private List<String> resizeList;
}
