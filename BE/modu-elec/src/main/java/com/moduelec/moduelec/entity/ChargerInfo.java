package com.moduelec.moduelec.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ChargerInfo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String address;
    Double longitude;
    Double latitude;
    Integer startHour;
    Integer endHour;
    String type;
    Integer pricePerHour;
}
