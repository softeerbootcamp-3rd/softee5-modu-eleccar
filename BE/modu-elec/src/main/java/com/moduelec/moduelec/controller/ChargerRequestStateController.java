package com.moduelec.moduelec.controller;

import com.moduelec.moduelec.dto.ChargerRequestState;
import com.moduelec.moduelec.service.ChargerRequestStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChargerRequestStateController {
    private final ChargerRequestStateService chargerRequestStateService;

    @GetMapping("/charger/request/state")
    public ResponseEntity<ChargerRequestState> getChargerRequestState(
            @RequestParam("eventId") Long id
    ) {
        ChargerRequestState chargerRequestState = chargerRequestStateService.getChargerRequestState(id);
        return new ResponseEntity<>(chargerRequestState, HttpStatus.OK);
    }
}
