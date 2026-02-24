package com.metacoding.springv2.admin;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

/**
 * 관리자 비즈니스 로직 서비스 클래스.
 * ADMIN 권한 관련 트랜잭션 처리를 담당한다.
 */
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class AdminService {

}
