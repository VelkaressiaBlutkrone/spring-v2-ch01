package com.metacoding.springv2.core.handler.ex;

/**
 * 400 Bad Request 커스텀 예외 클래스.
 * 클라이언트의 잘못된 요청(입력값 유효성 실패 등)에 대해 발생시키며,
 * GlobalExceptionHandler에서 캐치하여 400 상태코드로 응답한다.
 */
public class Exception400 extends RuntimeException {
    public Exception400(String message) {
        super(message);
    }
}