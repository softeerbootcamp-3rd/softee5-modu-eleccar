package com.moduelec.moduelec.repository;

import com.moduelec.moduelec.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
