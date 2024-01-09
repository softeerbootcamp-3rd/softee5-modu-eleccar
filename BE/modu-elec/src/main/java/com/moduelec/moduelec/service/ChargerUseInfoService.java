package com.moduelec.moduelec.service;

import com.moduelec.moduelec.dto.ChargerUseInfoDto;
import com.moduelec.moduelec.entity.ChargerInfo;
import com.moduelec.moduelec.entity.Event;
import com.moduelec.moduelec.entity.EventHour;
import com.moduelec.moduelec.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChargerUseInfoService {
    private final EventRepository eventRepository;

    public ChargerUseInfoDto getChargerUseInfo(Long userId, Long eventId) {
        Event event = eventRepository.findById(eventId).get();
        List<EventHour> eventHours = event.getEventHours();

        ChargerUseInfoDto dto = new ChargerUseInfoDto();

        ChargerInfo chargerInfo = eventHours.get(0).getChargerInfo();

        Integer reservedStartHour = eventHours.get(0).getStartHour();
        Integer reservedEndHour = eventHours.get(eventHours.size()-1).getStartHour() + 1;


        dto.setReservedStartHour(reservedStartHour);
        dto.setReservedEndHour(reservedEndHour);
        dto.setAddress(chargerInfo.getAddress());
        dto.setExpectedChargeAmount(chargerInfo.getSpeed() * (reservedEndHour - reservedStartHour));
        dto.setTotalPrice(chargerInfo.getPricePerHour() * (reservedEndHour - reservedStartHour));
        dto.setMessage(chargerInfo.getMessage());

        return dto;
    }


}
