package com.sparta.fbpractice.Controller;

import com.sparta.fbpractice.dto.LoginRequestDto;
import com.sparta.fbpractice.dto.RegisterRequestDto;
import com.sparta.fbpractice.dto.RegisterResponseDto;
import com.sparta.fbpractice.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.Statement;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final JdbcTemplate jdbcTemplate;

    public LoginController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 회원가입 API
    @PostMapping("/register")
    public RegisterResponseDto registerUser(@RequestBody RegisterRequestDto requestDto) {
        // 아이디 중복 확인
        String checkQuery = "SELECT COUNT(*) FROM users WHERE userid = ?";
        Integer count = jdbcTemplate.queryForObject(checkQuery, new Object[]{requestDto.getUserid()}, Integer.class);

        if (count != null && count > 0) {
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }

        // 비밀번호를 해싱 없이 그대로 저장
        String rawPassword = requestDto.getPassword();

        // RequestDto -> Entity
        User user = new User(requestDto.getUserid(), rawPassword);

        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO users (userid, password) VALUES (?, ?)";

        jdbcTemplate.update(con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1, user.getUserid());
                    preparedStatement.setString(2, user.getPassword());  // 평문 비밀번호 저장
                    return preparedStatement;
                },
                keyHolder);

        // 기본키 저장
        Long id = keyHolder.getKey().longValue();
        user.setId(id);

        return new RegisterResponseDto(user);
    }


    // 로그인 API
    @PostMapping("/login")
    public RegisterResponseDto loginUser(@RequestBody LoginRequestDto requestDto) {
        // 사용자 정보 조회
        String sql = "SELECT id, userid, password FROM users WHERE userid = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, new Object[]{requestDto.getUserid()},
                    (rs, rowNum) -> new User(
                            rs.getLong("id"),
                            rs.getString("userid"),
                            rs.getString("password")
                    )
            );

            // 비밀번호 비교 (해싱 없이)
            if (!user.getPassword().equals(requestDto.getPassword())) {
                throw new RuntimeException("비밀번호가 일치하지 않습니다.");
            }

            // 로그인 성공
            return new RegisterResponseDto(user);

        } catch (DataAccessException e) {
            throw new RuntimeException("존재하지 않는 사용자입니다.");
        }
    }

}
