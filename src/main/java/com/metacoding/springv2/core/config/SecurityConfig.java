package com.metacoding.springv2.core.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.metacoding.springv2.core.filter.JwtAuthorizationFilter;
import com.metacoding.springv2.core.util.JwtProvider;
import com.metacoding.springv2.core.util.RespFilter;

import lombok.RequiredArgsConstructor;

/**
 * Spring Security 설정 클래스.
 * JWT 기반 Stateless 인증 구조를 구성하며, CORS 정책, CSRF 비활성화,
 * URL별 접근 권한, 인증/인가 예외 핸들러, JWT 필터 등록 등을 설정한다.
 * 세션을 사용하지 않고 모든 인증을 JWT 토큰으로 처리한다.
 */
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final JwtProvider jwtProvider;

    @Value("${app.cors.allowed-origins}")
    private String allowedOrigins;

    // BCrypt 비밀번호 인코더 빈 등록
    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    // CORS 허용 정책 설정
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedHeader("*");
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedOriginPatterns(List.of(allowedOrigins.split(",")));
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", config);
        return source;
    }

    // Security 필터 체인 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.sameOrigin()));
        http.formLogin(form -> form.disable());
        http.httpBasic(basic -> basic.disable());
        http.csrf(configure -> configure.disable());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(new JwtAuthorizationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
        http.exceptionHandling(ex -> ex
                .authenticationEntryPoint(
                        (request, response, authException) -> RespFilter.fail(response, 401, "로그인 후 이용해주세요"))
                .accessDeniedHandler(
                        (request, response, accessDeniedException) -> RespFilter.fail(response, 403, "권한이 없습니다")));
        http.authorizeHttpRequests(
                authorize -> authorize
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/users/**", "/api/boards/**", "/api/replies/**")
                        .hasAnyRole("USER", "ADMIN")
                        .anyRequest().permitAll());

        return http.build();
    }
}
