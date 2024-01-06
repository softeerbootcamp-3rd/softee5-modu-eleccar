package com.moduelec.moduelec.service;

import com.moduelec.moduelec.dto.ChargerResponseInterface;
import com.moduelec.moduelec.repository.ChargerInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.moduelec.moduelec.dto.ChargerList.*;

@Service
@RequiredArgsConstructor
public class ChargerListService {
  private final ChargerInfoRepository chargerInfoRepository;

  public ChargerListResponse searchByDistance(Double longitude, Double latitude){
    List<ChargerResponseInterface> chargerInterfaces = chargerInfoRepository.
            findAllByDistance(latitude,longitude);
    List<ChargerResponse> chargerResponses = chargerInterfaces.stream().map(ChargerResponse::of).toList();

    return ChargerListResponse.of(chargerResponses);
  }
}
