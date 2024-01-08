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

        if(event.getReservation().getUser().getId().equals(userId)) {
            ChargerInfo chargerInfo = eventHours.get(0).getChargerInfo();

            dto.setReservedStartHour(eventHours.get(0).getStartHour());
            dto.setReservedEndHour(eventHours.get(eventHours.size()-1).getStartHour() + 1);
            dto.setAddress(chargerInfo.getAddress());
            dto.setMessage(chargerInfo.getMessage());
        }

        return dto;
    }


}
