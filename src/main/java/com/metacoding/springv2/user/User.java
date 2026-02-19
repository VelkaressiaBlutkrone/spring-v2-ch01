package com.metacoding.springv2.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.*;
import java.util.*;
import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;

/**
 * 사용자 엔티티 클래스.
 * user_tb 테이블과 매핑되며, Spring Security의 UserDetails를 구현한다.
 * roles 필드는 콤마 구분 문자열(예: "USER" 또는 "USER,ADMIN")로 저장되며,
 * getAuthorities()에서 "ROLE_" 접두사를 붙여 권한 목록으로 변환한다.
 */
@NoArgsConstructor
@Getter
@Entity
@Table(name = "user_tb")
public class User implements UserDetails {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Column(unique = true, length = 20, nullable = false)
    private String username;
    @Column(length = 60, nullable = false)
    private String password;
    @Column(length = 30, nullable = false)
    private String email;
    private String roles = "USER";

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public User(Integer id, String username, String password, String email, String roles, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.createdAt = createdAt;
    }

    // 이메일과 비밀번호 수정
    public void update(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // 사용자 권한(역할) 수정
    public void updateRoles(String roles) {
        this.roles = roles;
    }

    // roles 문자열을 ROLE_ 접두사가 붙은 권한 목록으로 변환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        String[] roleList = roles.split(",");

        for (String role : roleList) {
            authorities.add(() -> "ROLE_" + role);
        }
        return authorities;
    }
}