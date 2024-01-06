package com.moduelec.moduelec.controller;

import com.moduelec.moduelec.dto.ChargerList;
import com.moduelec.moduelec.service.ChargerListService;
import com.moduelec.moduelec.service.ChargerReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChargerController {
  private final ChargerListService chargerListService;
  private final ChargerReservationService chargerReservationService;
  @GetMapping("/charger/list")
  public ChargerList.ChargerListResponse searchChargerList(@RequestParam(value = "latitude")Double latitude,
                                                        @RequestParam(value = "longitude")Double longitude){
    return chargerListService.searchByDistance(longitude,latitude);
  }
  @PostMapping("/charger/reservation")
  public Boolean makeReservation(@RequestParam(value = "startHour") Integer startHour,
                                 @RequestParam(value = "chargerInfoId") Long chargerInfoId,
                                 @RequestParam(value = "chargeDuration") Integer chargeDuration,
                                 @RequestParam(value = "userId") Long userId){
    chargerReservationService.createEvent(startHour,chargeDuration,userId,chargerInfoId);
    return true;
  }
}
