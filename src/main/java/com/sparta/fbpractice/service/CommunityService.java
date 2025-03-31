package com.sparta.fbpractice.service;


import com.sparta.fbpractice.dto.CommunityRequestDto;
import com.sparta.fbpractice.dto.CommunityResponseDto;
import com.sparta.fbpractice.entity.Community;
import com.sparta.fbpractice.repository.CommunityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityService {
    private final CommunityRepository communityRepository;

    // 게시글 생성
    public CommunityResponseDto createCommunity(CommunityRequestDto communityRequestDto) {
        Community community = new Community(communityRequestDto);
        Community savedCommunity = communityRepository.save(community);
        return new CommunityResponseDto(savedCommunity);
    }

    // 전체 게시글 조회
    public List<CommunityResponseDto> getCommunities() {
        return communityRepository.findAll().stream()
                .map(CommunityResponseDto::new)
                .toList();
    }

    // 게시글 수정
    @Transactional
    public Long updateCommunity(Long id, CommunityRequestDto communityRequestDto) {
        Community community = findCommunity(id);
        community.update(communityRequestDto);
        return id;
    }

    // 게시글 삭제
    public Long deleteCommunity(Long id) {
        Community community = findCommunity(id);
        communityRepository.delete(community);
        return id;
    }

    private Community findCommunity(Long id) {
        return communityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
    }
}