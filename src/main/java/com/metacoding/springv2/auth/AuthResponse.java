package com.metacoding.springv2.auth;

import com.metacoding.springv2.user.User;

/**
 * 인증 응답 DTO 클래스.
 * User 엔티티의 민감 정보(비밀번호 등)를 제외하고 클라이언트에 반환할 데이터를 정의한다.
 */
public class AuthResponse {
    public record DTO(Integer id, String username, String email, String roles) {
        // User 엔티티를 응답 DTO로 변환
        public DTO(User user) {
            this(user.getId(), user.getUsername(), user.getEmail(), user.getRoles());
        }
    }
}