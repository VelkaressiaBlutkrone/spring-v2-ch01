package com.metacoding.springv2.user;

import java.sql.Timestamp;

import lombok.Data;

/**
 * 사용자 정보 응답용 DTO 클래스.
 * 클라이언트에게 반환될 사용자 관련 데이터를 정의합니다.
 */
public class UserResponse {

    @Data
    public static class DTO {
        private Integer id;
        private String username;
        private String email;
        private Timestamp createdAt;

        public DTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();
            this.createdAt = user.getCreatedAt();
        }
    }
}
