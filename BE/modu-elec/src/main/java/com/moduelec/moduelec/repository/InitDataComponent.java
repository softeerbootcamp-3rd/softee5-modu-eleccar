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

    ChargerInfo chargerInfo1 = new ChargerInfo(null,"서울 중구 을지로1가 42",126.98019168625157,37.56668932161417,8,19,"아파트",
            "완속",7,"실외 스탠드형","RFID 카드키","https://encrypted-tbn0.gstatic.com/images?q=tbn:" +
            "ANd9GcSyc6avEz6tPwB2hgA9PoMp9wtvVYLrEW9Ffg&usqp=CAU",1540,
            "제가 지금 집에 없어서 남편에게 말해두었으니 현관벨 누르시면 카드 드릴게요 그리고 외진 곳이라..." +
                    " 아마 오시는 길이 많이 어려울 거예요. 혹시 위치 헷갈리심 챗 주셔도 괜찮습니다","주차하기 좋아요!",alice);

    ChargerInfo chargerInfo2 = new ChargerInfo(null,"서울 종로구 신문로2가 171",126.97219973069245,37.56861584111746,12,22,"공동주택",
            "완속",10,"캐노피","RFID 카드키","https://encrypted-tbn0.gstatic.com/images?q=t" +
            "bn:ANd9GcQgKppTu5DhPmaDHNZ56M8YLjkGkCPO_Rh-og&usqp=CAU",1540,"기둥뒤에 공간있어요, 잘 고장나요.","저렴해요",alice);

    ChargerInfo chargerInfo3 = new ChargerInfo(null,"서울 중구 정동 5-1",126.97480428053937,37.56533680077833,14,20,"단독주택",
            "완속",8,"실외 스탠드형","비밀번호","https://encrypted-tbn0.gstatic.com/images?q=tbn:" +
            "ANd9GcSyc6avEz6tPwB2hgA9PoMp9wtvVYLrEW9Ffg&usqp=CAU",1600,"충격에 민감해요.","여기로 오세요!",alice);

    chargerInfoRepository.save(chargerInfo1);
    chargerInfoRepository.save(chargerInfo2);
    chargerInfoRepository.save(chargerInfo3);
  }
}
