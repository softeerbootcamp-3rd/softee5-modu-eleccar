package com.moduelec.moduelec.repository;

import com.moduelec.moduelec.entity.EventHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventHourRepository extends JpaRepository<EventHour, Long> {

    @Query("SELECT eh.startHour FROM EventHour eh " +
            "JOIN eh.event e " +
            "JOIN e.reservation r " +
            "WHERE eh.chargerInfo.id = :chargerInfoId " +
            "AND r.id IS NOT NULL")
    List<Integer> findReservedStartHours(@Param("chargerInfoId") Long chargerInfoId);
}
