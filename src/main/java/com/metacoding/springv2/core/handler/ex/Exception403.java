package com.metacoding.springv2.core.handler.ex;

/**
 * 403 Forbidden 커스텀 예외 클래스.
 * 인가 실패(권한 부족)에 대해 발생시키며,
 * GlobalExceptionHandler에서 캐치하여 403 상태코드로 응답한다.
 */
public class Exception403 extends RuntimeException {
    public Exception403(String message) {
        super(message);
    }
}