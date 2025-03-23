package com.sparta.fbpractice.entity;

import com.sparta.fbpractice.dto.RegisterRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private Long id;
    private String userid;
    private String password;

    // 회원가입 시 사용하는 생성자
    public User(RegisterRequestDto requestDto) {
        this.userid = requestDto.getUserid();
        this.password = requestDto.getPassword();
    }

    // 데이터베이스에서 조회할 때 사용하는 생성자
    public User(Long id, String userid, String password) {
        this.id = id;
        this.userid = userid;
        this.password = password;
    }
    public User(String userid, String password) {
        this.userid = userid;
        this.password = password;
    }
}
