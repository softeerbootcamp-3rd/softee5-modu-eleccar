package com.moduelec.moduelec.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ChargerDetailResponse {
    private Long chargerInfoId;
    private Integer pricePerHour;
    private Double distance;
    private Integer startHour;
    private Integer endHour;
    private String housingType;
    private String chargerType;
    private Integer speed;
    private String installType;
    private String useType;
    private String imageUrl;
    private String address;
    private String title;
}
