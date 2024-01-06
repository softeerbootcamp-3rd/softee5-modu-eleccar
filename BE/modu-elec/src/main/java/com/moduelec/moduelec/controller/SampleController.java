package com.moduelec.moduelec.controller;

import com.moduelec.moduelec.entity.Sample;
import com.moduelec.moduelec.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SampleController {

  private final SampleRepository sampleRepository;

  @GetMapping("/hello-world")
  public List<Sample> getAllSample(){
    return sampleRepository.findAll();
  }
  @PostMapping("/hello-world")
  public void insert(){
    sampleRepository.save(new Sample(null,"abc"));
  }
}
