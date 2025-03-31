package com.sparta.fbpractice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 모든 경로에 대해 CORS를 허용
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // 클라이언트 주소 허용
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드 설정
                .allowedHeaders("*") // 허용할 헤더
                .allowCredentials(true); // 자격 증명 허용 (쿠키나 인증 정보를 요청에 포함)
    }
}
