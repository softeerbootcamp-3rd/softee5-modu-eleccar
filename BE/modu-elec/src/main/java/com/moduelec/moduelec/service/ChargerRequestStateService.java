package com.moduelec.moduelec.service;

import com.moduelec.moduelec.dto.ChargerRequestState;
import com.moduelec.moduelec.entity.Event;
import com.moduelec.moduelec.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChargerRequestStateService {
    private final EventRepository eventRepository;

    public ChargerRequestState getChargerRequestState(Long id) {
        return eventRepository.findById(id)
                .map(event -> event.getReservation() == null ?
                        ChargerRequestState.PENDING : ChargerRequestState.ACCEPTED)
                .orElse(ChargerRequestState.DENIED);
    }
}
