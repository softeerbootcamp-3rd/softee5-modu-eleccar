package com.moduelec.moduelec.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.gson.JsonArray;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
@Slf4j
public class FCMConfig {
  @Value("${fcm.path}")
  private String fcmPath;
  @PostConstruct
  public void getFcmCredential(){
    try {
      // JSON 파일 읽기
      InputStream refreshToken = new FileInputStream(fcmPath);

      FirebaseOptions options = FirebaseOptions.builder()
              .setCredentials(GoogleCredentials.fromStream(refreshToken)).build();

      FirebaseApp.initializeApp(options);
      log.info("Fcm Setting Completed");
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
