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
    private int totalSize;
    private List<ChargerResponse> chargers;

    public static ChargerListResponse of(List<ChargerResponse> chargers){
      return new ChargerListResponse(chargers.size(),chargers);
    }
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Getter
  public static class ChargerResponse{
    private Long chargerInfoId;
    private Integer speed;
    private Double distance;
    private Integer pricePerHour;
    private Integer startHour;
    private Integer endHour;
    private Double latitude;
    private Double longitude;
    private String title;

    public static ChargerResponse of(ChargerResponseInterface c){
      return new ChargerResponse(c.getChargerInfoId(),c.getSpeed(),c.getDistance(),c.getPricePerHour(),
              c.getStartHour(),c.getEndHour(),c.getLatitude(),c.getLongitude(),c.getTitle());
    }
  }
}
