package com.moduelec.moduelec.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ChargerState {
    private Integer startHour;
    private boolean available;
}
