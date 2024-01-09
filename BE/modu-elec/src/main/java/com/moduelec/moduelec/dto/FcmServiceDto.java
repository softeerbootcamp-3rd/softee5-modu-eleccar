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
    return new FcmServiceDto("sample body message","sample title",
            "https://avatars.githubusercontent.com/u/136432473?v=4","confirm page",eventId);
  }
}
