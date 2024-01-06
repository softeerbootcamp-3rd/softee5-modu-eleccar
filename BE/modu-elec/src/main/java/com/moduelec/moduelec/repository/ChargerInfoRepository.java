package com.moduelec.moduelec.repository;

import com.moduelec.moduelec.dto.ChargerResponseInterface;
import com.moduelec.moduelec.entity.ChargerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChargerInfoRepository extends JpaRepository<ChargerInfo,Long> {
  @Query(value = "SELECT c.id as chargerInfoId, c.type as type, " +
          "2 * 6371 * ASIN(SQRT(POWER(SIN((RADIANS(37.5642135) - RADIANS(c.latitude)) / 2), 2) " +
          "+ COS(RADIANS(37.5642135)) * COS(RADIANS(c.latitude)) * POWER(SIN((RADIANS(127.0016985) " +
          "- RADIANS(c.longitude)) / 2), 2)))" +
          " as distance," +
          "c.price_per_hour as pricePerHour, c.start_hour as startHour, c.end_hour as endHour " +
          "FROM charger_info c WHERE " +
          "2 * 6371 * ASIN(SQRT(POWER(SIN((RADIANS(37.5642135) - RADIANS(c.latitude)) / 2), 2) " +
          "+ COS(RADIANS(37.5642135)) * COS(RADIANS(c.latitude)) * POWER(SIN((RADIANS(127.0016985) " +
          "- RADIANS(c.longitude)) / 2), 2)))" +
          "<=3000",
          nativeQuery = true)
  List<ChargerResponseInterface> findAllByDistance(@Param("latitude")Double latitude,
                                                   @Param("longitude")Double longitude);
}
