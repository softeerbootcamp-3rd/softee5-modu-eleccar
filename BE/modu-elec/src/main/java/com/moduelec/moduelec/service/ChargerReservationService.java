package com.moduelec.moduelec.service;

import com.moduelec.moduelec.entity.*;
import com.moduelec.moduelec.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChargerReservationService {
  private final EventRepository eventRepository;
  private final EventHourRepository eventHourRepository;
  private final UserRepository userRepository;
  private final ChargerInfoRepository chargerInfoRepository;
  private final ReservationRepository reservationRepository;

  @Transactional
  public Long createEvent(int startHour, int duration, Long userId, Long chargerInfoId){
    User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("invalid user id ["+userId+"]"));
    ChargerInfo chargerInfo = chargerInfoRepository
            .findById(chargerInfoId).orElseThrow(()->new RuntimeException("invalid charger info id ["+chargerInfoId+"]"));
    Event event = new Event(null,user, LocalDateTime.now());
    eventRepository.save(event);

    List<EventHour> eventHours = new ArrayList<>();
    for(int i=0;i<duration;i++){
      eventHours.add(new EventHour(null,chargerInfo,startHour+i,event));
    }
    eventHourRepository.saveAll(eventHours);
    return event.getId();
  }

  @Transactional
  public void acceptEvent(Long eventId){
    Event acceptedEvent = eventRepository.findById(eventId)
            .orElseThrow(()->new RuntimeException("invalid event id ["+eventId+"]"));
  }
}
