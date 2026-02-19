package com.metacoding.springv2.core.handler.ex;

/**
 * 401 Unauthorized 커스텀 예외 클래스.
 * 인증 실패(로그인 실패, 토큰 만료 등)에 대해 발생시키며,
 * GlobalExceptionHandler에서 캐치하여 401 상태코드로 응답한다.
 */
public class Exception401 extends RuntimeException {
    public Exception401(String message) {
        super(message);
    }
}