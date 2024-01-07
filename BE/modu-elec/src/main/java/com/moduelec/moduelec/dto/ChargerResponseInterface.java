package com.moduelec.moduelec.dto;

public interface ChargerResponseInterface {
  Long getChargerInfoId();
  String getType();
  Double getDistance();
  Integer getPricePerHour();
  Integer getStartHour();
  Integer getEndHour();
}
