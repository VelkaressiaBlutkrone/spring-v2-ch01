package com.metacoding.springv2.user;

import java.util.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.metacoding.springv2.auth.*;
import com.metacoding.springv2.core.handler.ex.*;
import com.metacoding.springv2.core.util.JwtUtil;
import lombok.RequiredArgsConstructor;

/**
 * 사용자 비즈니스 로직 서비스 클래스.
 * 회원가입, 로그인, 유저네임 중복체크 등 사용자 관련 트랜잭션 처리를 담당한다.
 * 비밀번호는 BCrypt로 암호화하여 저장하며, 로그인 성공 시 JWT 토큰을 생성하여 반환한다.
 */
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원가입 처리 (비밀번호 암호화 후 저장)
    @Transactional
    public AuthResponse.DTO 회원가입(AuthRequest.JoinDTO requestDTO) {
        if (userRepository.findByUsername(requestDTO.username()).isPresent())
            throw new Exception401("이미 존재하는 유저네임입니다");
        String encPassword = bCryptPasswordEncoder.encode(requestDTO.password());
        User savedUser = userRepository.save(requestDTO.toEntity(encPassword));
        return new AuthResponse.DTO(savedUser);
    }

    // 로그인 처리 (비밀번호 검증 후 JWT 토큰 반환)
    public String 로그인(AuthRequest.LoginDTO requestDTO) {
        User findUser = userRepository.findByUsername(requestDTO.username())
                .orElseThrow(() -> new Exception404("유저네임 혹은 비밀번호가 일치하지 않습니다"));
        if (!bCryptPasswordEncoder.matches(requestDTO.password(), findUser.getPassword()))
            throw new Exception401("유저네임 혹은 비밀번호가 일치하지 않습니다");
        return JwtUtil.create(findUser);
    }

    // 유저네임 사용 가능 여부 확인
    public Map<String, Object> 유저네임중복체크(String username) {
        Map<String, Object> mapDTO = new HashMap<>();
        if (userRepository.findByUsername(username).isPresent()) {
            mapDTO.put("available", false);
        } else {
            mapDTO.put("available", true);
        }
        return mapDTO;
    }
}