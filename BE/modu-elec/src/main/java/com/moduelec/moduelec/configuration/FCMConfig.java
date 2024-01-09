package com.moduelec.moduelec.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
@Slf4j
public class FCMConfig {
  @PostConstruct
  public void getFcmCredential(){
    try {
      ClassPathResource resource = new ClassPathResource("/firebase-adminsdk.json");
      InputStream refreshToken = resource.getInputStream();

      FirebaseOptions options = FirebaseOptions.builder()
              .setCredentials(GoogleCredentials.fromStream(refreshToken)).build();

      FirebaseApp.initializeApp(options);
      log.info("Fcm Setting Completed");
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
