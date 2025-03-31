package com.sparta.fbpractice.dto;

import com.sparta.fbpractice.entity.Community;
import lombok.Getter;

@Getter
public class CommunityResponseDto {
    private Long id;  // int → Long으로 변경 (JPA에서는 보통 Long을 사용)
    private String userid;
    private String contents;

    // Community 엔티티를 받아서 필드를 초기화하는 생성자 추가
    public CommunityResponseDto(Community community) {
        this.id = community.getId();
        this.userid = community.getUserid();
        this.contents = community.getContents();
    }
}