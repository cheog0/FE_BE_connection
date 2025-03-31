package com.sparta.fbpractice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter  // @Setter 추가하여 setter 메서드 생성
public class CommunityRequestDto {
    private String contents;
    private String userid;
}