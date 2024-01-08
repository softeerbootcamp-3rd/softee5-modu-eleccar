package com.moduelec.moduelec.service;

import com.moduelec.moduelec.dto.ChargerRequestStateDto;
import com.moduelec.moduelec.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChargerRequestStateService {
    private final EventRepository eventRepository;

    public ChargerRequestStateDto getChargerRequestState(Long id) {
        String state = eventRepository.findById(id)
                .map(event -> event.getReservation() == null ? "PENDING" : "ACCEPTED")
                .orElse("DENIED");

        ChargerRequestStateDto dto = new ChargerRequestStateDto();
        dto.setChargerRequestState(state);
        if (state.equals("ACCEPTED")) {
            dto.setEventId(id);
        }
        return dto;
    }
}
