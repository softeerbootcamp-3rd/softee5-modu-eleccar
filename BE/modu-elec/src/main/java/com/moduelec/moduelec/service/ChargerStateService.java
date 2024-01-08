package com.moduelec.moduelec.service;

import com.moduelec.moduelec.dto.ChargerState;
import com.moduelec.moduelec.entity.ChargerInfo;
import com.moduelec.moduelec.repository.ChargerInfoRepository;
import com.moduelec.moduelec.repository.EventHourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChargerStateService {

    private final ChargerInfoRepository chargerInfoRepository;
    private final EventHourRepository eventHourRepository;

    public List<ChargerState> getChargerState(Long id) {
        ChargerInfo chargerInfo = chargerInfoRepository.findById(id).get();

        Integer startHour = chargerInfo.getStartHour();
        Integer endHour = chargerInfo.getEndHour();
        List<Integer> reservedStartHours = eventHourRepository.findReservedStartHours(id);

        List<ChargerState> chargerStates = new ArrayList<>();
        for (int i = startHour; i < endHour; i++) {
            ChargerState chargerState = new ChargerState(i, false);
            if (!reservedStartHours.contains(i)) {
                chargerState.setAvailable(true);
            }
            chargerStates.add(chargerState);
        }

        return chargerStates;
    }
}
