package com.moduelec.moduelec.service;

import com.google.firebase.messaging.*;
import com.moduelec.moduelec.dto.FcmServiceDto;
import com.moduelec.moduelec.entity.User;
import com.moduelec.moduelec.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class FcmService {
  private final UserRepository userRepository;
  public void sendByToken(FcmServiceDto dto,Long userId){
    User sendingUser = userRepository.findById(userId)
            .orElseThrow(()->new RuntimeException("invalid userId ["+userId+"]"));
    String token = sendingUser.getFcmToken();

    Message message = Message.builder()
            .setToken(token)
            .setNotification(
                    Notification.builder()
                            .setTitle(dto.getTitle())
                            .setBody(dto.getBody())
                            .setImage(dto.getImageUrl())
                            .build()
            )
            .setAndroidConfig(
                    AndroidConfig.builder()
                            .setNotification(
                                    AndroidNotification.builder()
                                            .setTitle(dto.getTitle())
                                            .setBody(dto.getBody())
                                            .setImage(dto.getImageUrl())
                                            .setClickAction("push_click")
                                            .build()
                            )
                            .build()
            )
            .setApnsConfig(
                    ApnsConfig.builder()
                            .setAps(Aps.builder()
                                    .setCategory("push_click")
                                    .build())
                            .build()
            )
            .putData("eventId",String.valueOf(dto.getEventId()))
            .build();

    try {
      String response = FirebaseMessaging.getInstance().send(message);
      log.info("FCM send-"+response);
    } catch (FirebaseMessagingException e) {
      log.info("FCM except-"+ e.getMessage());
    }
  }
}
