package com.moduelec.moduelec.repository;

import com.moduelec.moduelec.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
