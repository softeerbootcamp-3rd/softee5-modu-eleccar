package com.moduelec.moduelec.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    User user;
    LocalDateTime createdAt;
    @OneToMany(mappedBy = "event")
    List<EventHour> eventHours;

    public static Event create(User user){
        return new Event(null,user,LocalDateTime.now(),null);
    }
}
