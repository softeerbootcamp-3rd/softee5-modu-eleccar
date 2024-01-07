package com.moduelec.moduelec.controller;

import com.moduelec.moduelec.dto.ChargerState;
import com.moduelec.moduelec.entity.ChargerInfo;
import com.moduelec.moduelec.repository.ChargerInfoRepository;
import com.moduelec.moduelec.repository.EventHourRepository;
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

    private final ChargerInfoRepository chargerInfoRepository;
    private final EventHourRepository eventHourRepository;
    @GetMapping("/charger/state")
    public List<ChargerState> getChargerState(
            @RequestParam("chargerInfoId") Long id
    ) {
        ChargerInfo chargerInfo = chargerInfoRepository.findById(id).get();
        Integer startHour = chargerInfo.getStartHour();
        Integer endHour = chargerInfo.getEndHour();
        List<Integer> reservedStartHours = eventHourRepository.findReservedStartHours(id);

        List<ChargerState> chargerStates = new ArrayList<>();
        for(int i=0; i<24; i++) {
            ChargerState chargerState = new ChargerState(i, false);
            if(i>=startHour && i<endHour && !reservedStartHours.contains(i)) {
                chargerState.setAvailable(true);
            }
            chargerStates.add(chargerState);
        }

        return chargerStates;
    }
}
