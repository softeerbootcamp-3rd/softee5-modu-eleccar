package com.moduelec.moduelec.service;

import com.moduelec.moduelec.dto.FcmServiceDto;
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
  private final FcmService fcmService;

  @Transactional
  public Long createEvent(int startHour, int duration, Long userId, Long chargerInfoId){
    User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("invalid user id ["+userId+"]"));
    ChargerInfo chargerInfo = chargerInfoRepository
            .findById(chargerInfoId).orElseThrow(()->new RuntimeException("invalid charger info id ["+chargerInfoId+"]"));
    Event event = Event.create(user);
    eventRepository.save(event);

    List<EventHour> eventHours = new ArrayList<>();
    for(int i=0;i<duration;i++){
      eventHours.add(EventHour.create(chargerInfo,startHour+i,event));
    }
    eventHourRepository.saveAll(eventHours);

    fcmService.sendByToken(FcmServiceDto.create(event.getId()),userId);
    return event.getId();
  }

  @Transactional
  public void acceptEvent(Event acceptedEvent, User user){
    //accept flow
    Reservation reservation = new Reservation(null,user,acceptedEvent);

    //save repo
    reservationRepository.save(reservation);
  }
  @Transactional
  public void rejectEvent(Event rejectedEvent){
    //reject flow
    eventHourRepository.deleteAll(rejectedEvent.getEventHours());
    eventRepository.delete(rejectedEvent);
  }
  public void eventConfirmProcess(Long eventId, Long sellerUserId, Boolean acceptEvent){
    //get entities from db
    Event event = eventRepository.findById(eventId)
            .orElseThrow(()->new RuntimeException("invalid event id ["+eventId+"]"));
    User user = userRepository.findById(sellerUserId)
            .orElseThrow(()->new RuntimeException("invalid user id ["+sellerUserId+"]"));
    User chargerOwnerOfEvent = getChargerInfoOwner(event);
    //authority validation
    if (!isSameUser(user,chargerOwnerOfEvent))
      throw new RuntimeException("mismatch between user and charger owner");
    if (acceptEvent)
      acceptEvent(event,user);
    else
      rejectEvent(event);
  }
  private Boolean isSameUser(User user1,User user2){
    if (user1.getId()==null || user2.getId()==null)
      return false;
    return user1.getId().equals(user2.getId());
  }
  private User getChargerInfoOwner(Event acceptedEvent){
    List<EventHour> eventHours = acceptedEvent.getEventHours();
    if (eventHours.size()==0)
      throw new RuntimeException("reservation hours cannot be empty");
    EventHour firstEventHour = eventHours.get(0);

    ChargerInfo chargerInfo = firstEventHour.getChargerInfo();

    return chargerInfo.getUser();
  }
}
