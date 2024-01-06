package com.moduelec.moduelec.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class EventHour {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    ChargerInfo chargerInfo;
    Integer startHour;

    @ManyToOne
    Event event;
}