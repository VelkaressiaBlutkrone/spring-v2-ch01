package com.metacoding.springv2.core.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.metacoding.springv2.core.util.JwtProvider;
import com.metacoding.springv2.core.util.JwtUtil;
import com.metacoding.springv2.user.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * JWT 인가 필터 클래스.
 * OncePerRequestFilter를 상속하여 매 요청마다 한 번씩 실행된다.
 * Authorization 헤더의 JWT 토큰을 검증하고 SecurityContext에 인증 정보를 저장하는 역할을 한다.
 * UsernamePasswordAuthenticationFilter 이전에 동작하여 JWT 기반 인증을 선처리한다.
 */
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    // 요청마다 JWT 토큰을 검증하고 인증 처리
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // localhost:8080/api/good
        // header -> Authorization : Bearer JWT토큰

        String jwt = request.getHeader("Authorization");

        if (jwt == null) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = jwt.replace("Bearer ", "");

        User user = JwtUtil.verify(jwt);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
