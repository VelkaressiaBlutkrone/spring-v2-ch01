package com.metacoding.springv2.core.handler.ex;

/**
 * 404 Not Found 커스텀 예외 클래스.
 * 요청한 자원을 찾을 수 없을 때 발생시키며,
 * GlobalExceptionHandler에서 캐치하여 404 상태코드로 응답한다.
 */
public class Exception404 extends RuntimeException {
    public Exception404(String message) {
        super(message);
    }
}