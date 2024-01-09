package com.moduelec.moduelec.controller;

import com.moduelec.moduelec.dto.FcmServiceDto;
import com.moduelec.moduelec.entity.Event;
import com.moduelec.moduelec.entity.Sample;
import com.moduelec.moduelec.entity.User;
import com.moduelec.moduelec.repository.SampleRepository;
import com.moduelec.moduelec.repository.UserRepository;
import com.moduelec.moduelec.service.FcmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SampleController {

  private final SampleRepository sampleRepository;
  private final FcmService fcmService;
  private final UserRepository userRepository;

  @GetMapping("/hello-world")
  public List<Sample> getAllSample(){
    return sampleRepository.findAll();
  }
  @PostMapping("/hello-world")
  public void insert(){
    sampleRepository.save(new Sample(null,"abc"));
  }
  @PostMapping("/sample/message")
  public void sendPushMessage(){
    Event event = Event.create(userRepository.findById(1L).orElseThrow(()->new RuntimeException("invalid user id")));
    fcmService.sendByToken(FcmServiceDto.create(event.getId()),1L);
  }
}
