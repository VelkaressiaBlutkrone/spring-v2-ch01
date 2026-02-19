package com.metacoding.springv2.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.metacoding.springv2.core.util.Resp;
import com.metacoding.springv2.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * 인증 관련 REST 컨트롤러.
 * 회원가입, 로그인, 유저네임 중복체크 등 인증이 필요 없는 공개 API를 제공한다.
 * 모든 엔드포인트는 permitAll로 설정되어 비인증 사용자도 접근 가능하다.
 */
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final UserService userService;

    // 회원가입 처리
    @PostMapping("/join")
    public ResponseEntity<?> join(@Valid @RequestBody AuthRequest.JoinDTO requestDTO, Errors errors) {
        var responseDTO = userService.회원가입(requestDTO);
        return Resp.ok(responseDTO);
    }

    // 로그인 처리 후 JWT 액세스 토큰 반환
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest.LoginDTO requestDTO) {
        String accessToken = userService.로그인(requestDTO);
        return ResponseEntity.ok(accessToken);
    }

    // 유저네임 사용 가능 여부 확인
    @GetMapping("/check-username")
    public ResponseEntity<?> getUsername(@RequestParam String username) {
        var responseDTO = userService.유저네임중복체크(username);
        return Resp.ok(responseDTO);
    }
}
