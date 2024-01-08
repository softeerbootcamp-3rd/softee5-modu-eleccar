package com.moduelec.moduelec.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventHour {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    ChargerInfo chargerInfo;
    Integer startHour;

    @ManyToOne
    Event event;

    public static EventHour create(ChargerInfo chargerInfo, Integer startHour, Event event){
        return new EventHour(null,chargerInfo,startHour,event);
    }
}
