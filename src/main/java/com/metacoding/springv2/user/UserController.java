package com.metacoding.springv2.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metacoding.springv2.core.util.Resp;

import lombok.RequiredArgsConstructor;

/**
 * 사용자 관련 REST 컨트롤러.
 * 사용자 정보 조회, 수정 등 인증된 사용자 전용 API 엔드포인트를 제공한다.
 */
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserController {

    private final UserService userService;

    /**
     * @param id
     * @return
     * @throws Exception404 해당 ID의 사용자를 찾을 수 없을 때 발생
     * 
     *                      - **목표**: 회원 ID로 특정 사용자의 정보를 조회하는 기능을 구현합니다.
     *                      - **API 엔드포인트**: `GET /api/users/{id}` (RESTful API 규칙 준수)
     *                      - **구현 위치**: `UserController` 및 `UserService`
     * 
     */
    // 등록된 사용자 조회
    @GetMapping("/users/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Integer id) {
        var responseDTO = userService.findUserById(id);
        return Resp.ok(responseDTO);
    }
}
