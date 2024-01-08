package com.moduelec.moduelec.dto;

public interface ChargerResponseInterface {
  Long getChargerInfoId();
  Integer getSpeed();
  Double getDistance();
  Integer getPricePerHour();
  Integer getStartHour();
  Integer getEndHour();
  Double getLatitude();
  Double getLongitude();
  String getTitle();
}
