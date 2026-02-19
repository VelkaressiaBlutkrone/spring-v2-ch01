package com.metacoding.springv2.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.metacoding.springv2.core.util.Resp;
import com.metacoding.springv2.user.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final UserService userService;
    private final AuthService authService;
    private HttpSession session;

    @PostMapping("/join")
    public ResponseEntity<?> join(@Valid @RequestBody AuthRequest.JoinDTO requestDTO, Errors errors) {
        var responseDTO = userService.회원가입(requestDTO);
        return Resp.ok(responseDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest.LoginDTO requestDTO) {
        // var responseDTO = userService.로그인(requestDTO);
        authService.로그인(requestDTO);
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());
        return "login";
    }

    @GetMapping("/check-username")
    public ResponseEntity<?> getUsername(@RequestParam String username) {
        var responseDTO = userService.유저네임중복체크(username);
        return Resp.ok(responseDTO);
    }
}
