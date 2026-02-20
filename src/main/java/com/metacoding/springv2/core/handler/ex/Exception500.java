package com.metacoding.springv2.core.handler.ex;

/**
 * 500 Internal Server Error 커스텀 예외 클래스.
 * 예상 가능한 서버 내부 오류에 대해 발생시키며,
 * GlobalExceptionHandler에서 캐치하여 500 상태코드로 응답한다.
 */
public class Exception500 extends RuntimeException {
    public Exception500(String message) {
        super(message);
    }
}