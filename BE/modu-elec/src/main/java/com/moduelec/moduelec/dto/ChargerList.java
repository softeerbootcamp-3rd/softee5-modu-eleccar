package com.moduelec.moduelec.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ChargerList {
  @NoArgsConstructor
  @AllArgsConstructor
  @Getter
  public static class ChargerListResponse{
    private int page;
    private int size;
    private boolean hasNext;
    private int totalSize;
    private List<ChargerResponse> chargers;
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Getter
  public static class ChargerResponse{
    private Long chargerInfoId;
    private String imageUrl;
    private String type;
    private Double distance;
    private Integer pricePerHour;
    private Integer startHour;
    private Integer endHour;
  }
}
