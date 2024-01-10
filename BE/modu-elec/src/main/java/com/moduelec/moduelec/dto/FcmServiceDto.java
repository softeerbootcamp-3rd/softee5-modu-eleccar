package com.moduelec.moduelec.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FcmServiceDto {
  private String body;
  private String title;
  private String imageUrl;
  private String linkedPage;
  private Long eventId;

  public static FcmServiceDto create(Long eventId){
    return new FcmServiceDto("소프티어 님이 내 카밥을 공유받고 싶어해요!","카밥 알림",
            "https://artique-profile.s3.ap-northeast-2.amazonaws.com/johan1103%40naver.com/app_icon.jpeg",
            "confirm page",eventId);
  }
}
