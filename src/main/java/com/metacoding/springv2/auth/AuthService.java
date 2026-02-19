package com.metacoding.springv2.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.metacoding.springv2.auth.AuthRequest.LoginDTO;
import com.metacoding.springv2.core.handler.ex.Exception401;
import com.metacoding.springv2.core.handler.ex.Exception404;
import com.metacoding.springv2.user.User;
import com.metacoding.springv2.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void 로그인(LoginDTO requestDTO) {
        User findUser = userRepository.findByUsername(requestDTO.username())
                .orElseThrow(() -> new Exception404("유저네임을 찾을수 없습니다."));

        if (!bCryptPasswordEncoder.matches(requestDTO.password(), findUser.getPassword()))
            throw new Exception401("비밀번호가 일치하지 않습니다");

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(findUser, null,
                findUser.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
