package com.moduelec.moduelec.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChargerInfoDto {
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
}
