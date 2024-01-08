package com.moduelec.moduelec.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ChargerInfo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String address; // 충전소 위치
    Double longitude; // 경도
    Double latitude; // 위도
    Integer startHour;
    Integer endHour;
    String housingType; // 충전 장소 형태 : 아파트, 공동주택, 단독주택, 숙박시설
    String chargerType; // 충전기 타입 : DC콤보, DC차데모, AC 3상, 완속, 수퍼차저, 데스티네이션
    Integer speed;
    String installType; // 설치 형태 : 실내, 실외, 캐노피
    String useType; // 사용 방식 : RFID카드키, 비밀번호
    String imageUrl; // 충전소 사진
    Integer pricePerHour;
    String message; // 판매자가 남기는 메시지
    @ManyToOne
    User user;
}
