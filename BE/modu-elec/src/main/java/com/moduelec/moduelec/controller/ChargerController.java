package com.moduelec.moduelec.controller;

import com.moduelec.moduelec.dto.ChargerList;
import com.moduelec.moduelec.service.ChargerListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChargerController {
  private final ChargerListService chargerListService;
  @GetMapping("/charger/list")
  public ChargerList.ChargerListResponse searchChargerList(@RequestParam(value = "latitude")Double latitude,
                                                        @RequestParam(value = "longitude")Double longitude){
    return chargerListService.searchByDistance(longitude,latitude);
  }
}
