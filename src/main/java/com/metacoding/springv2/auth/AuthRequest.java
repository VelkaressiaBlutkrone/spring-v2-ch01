package com.metacoding.springv2.auth;

import com.metacoding.springv2.user.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * 인증 요청 DTO 클래스.
 * 회원가입(JoinDTO)과 로그인(LoginDTO) 요청 데이터를 record 타입으로 정의한다.
 * Bean Validation 어노테이션을 통해 입력값 유효성 검증을 수행한다.
 */
public class AuthRequest {
    public record JoinDTO(
            @Size(min = 4, max = 20, message = "유저네임은 4자 이상 20자 이하로 입력해주세요") @NotEmpty(message = "유저네임을 입력해주세요") String username,
            @NotBlank(message = "비밀번호를 입력해주세요") @Size(min = 4, max = 60, message = "비밀번호는 4~60자여야 합니다") String password,
            @Email(message = "이메일 형식이 올바르지 않습니다") String email) {
        // JoinDTO를 User 엔티티로 변환
        public User toEntity(String encPassword) {
            return User.builder()
                    .username(username)
                    .password(encPassword)
                    .email(email)
                    .roles("USER")
                    .build();
        }
    }

    public record LoginDTO(
            @NotEmpty(message = "유저네임을 입력해주세요") String username,
            @NotBlank(message = "비밀번호를 입력해주세요") String password) {
    }
}
