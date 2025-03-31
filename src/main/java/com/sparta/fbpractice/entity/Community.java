package com.sparta.fbpractice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.sparta.fbpractice.dto.CommunityRequestDto;
import jakarta.persistence.*;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "Community") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid", nullable = false)
    private String userid;

    @Column(name = "contents", nullable = false, length = 500)
    private String contents;

    // CommunityRequestDto를 통해 Entity를 생성하는 생성자
    public Community(CommunityRequestDto communityRequestDto) {
        this.userid = communityRequestDto.getUserid();
        this.contents = communityRequestDto.getContents();
    }

    // 수정하는 메서드
    public void update(CommunityRequestDto communityRequestDto) {
        // 일반적으로 수정 시, userid는 변경되지 않으므로 contents만 수정할 수 있도록.
        this.contents = communityRequestDto.getContents();
    }
}