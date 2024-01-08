package com.moduelec.moduelec.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ChargerRequestStateDto {
    String chargerRequestState;
    Long EventId;
}
