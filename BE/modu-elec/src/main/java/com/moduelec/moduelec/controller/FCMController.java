package com.moduelec.moduelec.controller;

import com.moduelec.moduelec.entity.User;
import com.moduelec.moduelec.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FCMController {
  private final UserRepository userRepository;

  @PostMapping("/fcm/token")
  @Transactional
  public Boolean registerToken(@RequestParam("userId")Long userId, @RequestParam("token")String token){
    User user = userRepository.findById(userId)
            .orElseThrow(()->new RuntimeException("invalid id ["+userId+"]"));
    user.updateToken(token);
    return true;
  }
}
