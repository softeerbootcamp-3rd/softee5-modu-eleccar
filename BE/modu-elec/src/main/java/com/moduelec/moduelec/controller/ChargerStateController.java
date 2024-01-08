package com.moduelec.moduelec.controller;

import com.moduelec.moduelec.dto.ChargerState;
import com.moduelec.moduelec.entity.ChargerInfo;
import com.moduelec.moduelec.repository.ChargerInfoRepository;
import com.moduelec.moduelec.repository.EventHourRepository;
import com.moduelec.moduelec.service.ChargerStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ChargerStateController {

    private final ChargerStateService chargerStateService;
    @GetMapping("/charger/state")
    public List<ChargerState> getChargerState(@RequestParam("chargerInfoId") Long id) {
        return chargerStateService.getChargerState(id);
    }
}
