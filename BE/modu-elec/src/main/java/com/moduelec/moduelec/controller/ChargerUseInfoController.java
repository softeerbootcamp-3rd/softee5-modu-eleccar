package com.moduelec.moduelec.controller;

import com.moduelec.moduelec.dto.ChargerUseInfoDto;
import com.moduelec.moduelec.entity.ChargerInfo;
import com.moduelec.moduelec.entity.Event;
import com.moduelec.moduelec.entity.EventHour;
import com.moduelec.moduelec.repository.ChargerInfoRepository;
import com.moduelec.moduelec.repository.EventRepository;
import com.moduelec.moduelec.repository.UserRepository;
import com.moduelec.moduelec.service.ChargerUseInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChargerUseInfoController {
    private final ChargerUseInfoService chargerUseInfoService;

    @GetMapping("/charger/information")
    public ResponseEntity<ChargerUseInfoDto> getChargerUseInfo(
            @RequestParam("userId") Long userId,
            @RequestParam("eventId") Long eventId
    ) {
        ChargerUseInfoDto chargerUseInfoDto = chargerUseInfoService.getChargerUseInfo(userId, eventId);
        return ResponseEntity.ok(chargerUseInfoDto);
    }
}
