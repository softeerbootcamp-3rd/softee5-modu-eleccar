package com.moduelec.moduelec.controller;

import com.moduelec.moduelec.dto.ChargerDetailResponse;
import com.moduelec.moduelec.entity.ChargerInfo;
import com.moduelec.moduelec.repository.ChargerInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ChargerInfoController {

    private final ChargerInfoRepository chargerInfoRepository;
    @GetMapping("/charger/detail")
    public ResponseEntity<ChargerDetailResponse> getChargerInfoDetail(
            @RequestParam("chargerInfoId") Long id,
            @RequestParam("distance") double distance
    ) {

        Optional<ChargerInfo> chargerInfoOptional = chargerInfoRepository.findById(id);
        if(chargerInfoOptional.isPresent()) {
            ChargerInfo chargerInfo = chargerInfoOptional.get();
            return ResponseEntity.ok(makeDto(distance, chargerInfo));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    private static ChargerDetailResponse makeDto(double distance, ChargerInfo chargerInfo) {
        return ChargerDetailResponse.builder()
                .chargerInfoId(chargerInfo.getId())
                .pricePerHour(chargerInfo.getPricePerHour())
                .distance(distance)
                .startHour(chargerInfo.getStartHour())
                .endHour(chargerInfo.getEndHour())
                .housingType(chargerInfo.getHousingType())
                .chargerType(chargerInfo.getChargerType())
                .speed(chargerInfo.getSpeed())
                .installType(chargerInfo.getInstallType())
                .useType(chargerInfo.getUseType())
                .imageUrl(chargerInfo.getImageUrl())
                .build();
    }
}
