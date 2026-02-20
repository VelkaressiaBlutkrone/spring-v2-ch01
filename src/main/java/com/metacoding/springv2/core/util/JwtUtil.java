package com.metacoding.springv2.core.util;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.metacoding.springv2.user.User;

/**
 * JWT 토큰 생성 및 검증 유틸리티 클래스.
 * HMAC512 알고리즘을 사용하여 JWT 토큰을 생성하고 검증한다.
 * 토큰에는 사용자 ID, username, roles 정보가 포함되며 유효기간은 7일이다.
 */
public class JwtUtil {
    public static final String HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String SECRET = "메타코딩시크릿키";
    public static final Long EXPIRATION_TIME = 1000L * 60 * 60 * 24 * 7;

    // User 정보를 기반으로 JWT 토큰 생성
    public static String create(User user) {
        String jwt = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withClaim("id", user.getId())
                .withClaim("roles", user.getRoles())
                .sign(Algorithm.HMAC512(SECRET));

        return TOKEN_PREFIX + jwt;
    }

    // JWT 토큰을 검증하고 User 객체로 디코딩
    public static User verify(String jwt) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SECRET))
                .build()
                .verify(jwt);

        Integer id = decodedJWT.getClaim("id").asInt();
        String username = decodedJWT.getSubject();
        String roles = decodedJWT.getClaim("roles").asString();

        return User.builder().id(id).username(username).roles(roles).build();
    }
}
