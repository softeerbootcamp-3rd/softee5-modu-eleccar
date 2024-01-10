package com.moduelec.moduelec.repository;

import com.moduelec.moduelec.entity.ChargerInfo;
import com.moduelec.moduelec.entity.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitDataComponent {
  private final ChargerInfoRepository chargerInfoRepository;
  private final UserRepository userRepository;

  @PostConstruct
  public void initData(){
    User alice = new User(1L,"Alice",1000000000,null);
    User bob = new User(2L,"Bob",1000000000,null);

    userRepository.save(alice);
    userRepository.save(bob);

    ChargerInfo chargerInfo1 = new ChargerInfo(null,"123 SampleStreet",127.54321,37.12345,8,19,"Apartment",
            "FastCharger",7,"Outdoor","RFID Card Key","https://encrypted-tbn0.gstatic.com/images?q=tbn:" +
            "ANd9GcSyc6avEz6tPwB2hgA9PoMp9wtvVYLrEW9Ffg&usqp=CAU",1540,"특이사항은 ~~이구요, 잘 고장나요.","저렴해요",alice);

    ChargerInfo chargerInfo2 = new ChargerInfo(null,"123 SampleStreet",127.65432,37.23456,8,19,"Apartment",
            "SlowCharger",7,"Outdoor","RFID Card Key","https://encrypted-tbn0.gstatic.com/images?q=t" +
            "bn:ANd9GcQgKppTu5DhPmaDHNZ56M8YLjkGkCPO_Rh-og&usqp=CAU",1540,"기둥뒤에 공간있어요, 잘 고장나요.","저렴해요",alice);

    ChargerInfo chargerInfo3 = new ChargerInfo(null,"123 SampleStreet",127.76543,37.34567,8,19,"Apartment",
            "FastCharger",7,"Outdoor","Password","https://encrypted-tbn0.gstatic.com/images?q=tbn:" +
            "ANd9GcSyc6avEz6tPwB2hgA9PoMp9wtvVYLrEW9Ffg&usqp=CAU",1540,"특이사항은 ~~이구요, 충격에 민감해요.","주차하기 좋아요",alice);

    chargerInfoRepository.save(chargerInfo1);
    chargerInfoRepository.save(chargerInfo2);
    chargerInfoRepository.save(chargerInfo3);
  }
}
