package com.sparta.fbpractice.dto;

import com.sparta.fbpractice.entity.User;
import lombok.Getter;

@Getter
public class RegisterResponseDto {
    private Long id;
    private String userid;
    private String password;

    public RegisterResponseDto(User user) {
        this.id = user.getId();
        this.userid = user.getUserid();
        this.password = user.getPassword();
    }
    public RegisterResponseDto(Long id, String userid, String password) {
        this.id = id;
        this.userid = userid;
        this.password = password;
    }
}
