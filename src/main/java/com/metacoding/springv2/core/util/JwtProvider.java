package com.metacoding.springv2.core.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.metacoding.springv2.user.User;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

/**
 * JWT 토큰 처리를 담당하는 컴포넌트 클래스.
 * HTTP 요청에서 토큰 추출, 토큰 유효성 검증, 토큰 기반 Authentication 객체 생성 등
 * JwtAuthorizationFilter에서 사용하는 JWT 관련 기능을 제공한다.
 */
@RequiredArgsConstructor
@Component
public class JwtProvider {

    // Authorization 헤더에서 Bearer 토큰 추출
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(JwtUtil.HEADER);
        if (bearerToken != null && bearerToken.startsWith(JwtUtil.TOKEN_PREFIX)) {
            return bearerToken.replace(JwtUtil.TOKEN_PREFIX, "");
        }
        return null;
    }

    // 토큰을 검증하고 Authentication 객체 반환
    public Authentication getAuthentication(String token) {
        try {
            User user = JwtUtil.verify(token);
            return new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.getAuthorities());
        } catch (Exception e) {
            return null;
        }
    }

    // 토큰 유효성 여부 확인
    public boolean validateToken(String token) {
        try {
            JwtUtil.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
