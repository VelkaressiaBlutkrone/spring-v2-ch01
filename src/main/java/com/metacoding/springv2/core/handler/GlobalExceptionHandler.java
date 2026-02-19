package com.metacoding.springv2.core.handler;

import com.metacoding.springv2.core.handler.ex.*;
import com.metacoding.springv2.core.util.Resp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * 전역 예외 처리 클래스.
 * @RestControllerAdvice를 통해 컨트롤러에서 발생하는 모든 예외를 중앙에서 처리한다.
 * HTTP 상태코드별 커스텀 예외(400~500)와 예상 불가능한 예외를 각각 처리하여
 * 일관된 형식(Resp)의 에러 응답을 반환한다.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 400 Bad Request 예외 처리
    @ExceptionHandler(Exception400.class)
    public ResponseEntity<?> exApi400(Exception400 e) {
        log.warn("[WARN] 사용자 입력 유효성 실패: " + e.getMessage());
        return Resp.fail(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    // 401 Unauthorized 예외 처리
    @ExceptionHandler(Exception401.class)
    public ResponseEntity<?> exApi401(Exception401 e) {
        log.warn("[WARN] 사용자 인증 실패: " + e.getMessage());
        return Resp.fail(HttpStatus.UNAUTHORIZED, e.getMessage());
    }

    // 403 Forbidden 예외 처리
    @ExceptionHandler(Exception403.class)
    public ResponseEntity<?> exApi403(Exception403 e) {
        log.warn("[WARN] 사용자 권한 실패: " + e.getMessage());
        return Resp.fail(HttpStatus.FORBIDDEN, e.getMessage());
    }

    // 404 Not Found 예외 처리
    @ExceptionHandler(Exception404.class)
    public ResponseEntity<?> exApi404(Exception404 e) {
        log.warn("[WARN] 사용자 자원 찾기 실패: " + e.getMessage());
        return Resp.fail(HttpStatus.NOT_FOUND, e.getMessage());
    }

    // 500 Internal Server Error 예외 처리
    @ExceptionHandler(Exception500.class)
    public ResponseEntity<?> exApi500(Exception500 e) {
        log.warn("[ERROR] 예상 가능한 서버 오류: " + e.getMessage());
        return Resp.fail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    // 예상 불가능한 서버 오류 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exUnKnown(Exception e) {
        log.error("[SYSTEM] 예상 불가능한 서버 오류: " + e.getMessage());
        e.printStackTrace();
        return Resp.fail(HttpStatus.INTERNAL_SERVER_ERROR, "관리자에게 문의하세요");
    }
}