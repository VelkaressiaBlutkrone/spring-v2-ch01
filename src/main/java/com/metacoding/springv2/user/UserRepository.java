package com.metacoding.springv2.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * 사용자 JPA 리포지토리 인터페이스.
 * User 엔티티에 대한 데이터 접근 계층을 담당하며,
 * username 기반 조회 쿼리 메서드를 제공한다.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    // username으로 사용자 조회
    Optional<User> findByUsername(String username);
}