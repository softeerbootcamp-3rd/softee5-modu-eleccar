package com.moduelec.moduelec.sample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class SampleTest {
  @Autowired
  private SampleRepository sampleRepository;
  @Test
  @Rollback(value = false)
  public void saveSampleTest(){
    Sample sample = new Sample(null,"a");

    sampleRepository.save(sample);

    sampleRepository.flush();
  }
}
