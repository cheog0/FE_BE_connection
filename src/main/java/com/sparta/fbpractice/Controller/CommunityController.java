package com.sparta.fbpractice.Controller;

import com.sparta.fbpractice.dto.CommunityRequestDto;
import com.sparta.fbpractice.dto.CommunityResponseDto;
import com.sparta.fbpractice.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor  // 생성자 주입을 자동으로 해줌
public class CommunityController {

    private final CommunityService communityService;

    // 게시글 생성
    @PostMapping("/community")
    public CommunityResponseDto createCommunity(@RequestBody CommunityRequestDto communityRequestDto) {
        return communityService.createCommunity(communityRequestDto);
    }

    // 전체 게시글 조회
    @GetMapping("/community")
    public List<CommunityResponseDto> getCommunities() {
        return communityService.getCommunities();
    }

    // 특정 게시글 수정
    @PutMapping("/community/{id}")
    public Long updateCommunity(@PathVariable Long id, @RequestBody CommunityRequestDto communityRequestDto) {
        return communityService.updateCommunity(id, communityRequestDto);
    }

    // 특정 게시글 삭제
    @DeleteMapping("/community/{id}")
    public Long deleteCommunity(@PathVariable Long id) {
        return communityService.deleteCommunity(id);
    }
}