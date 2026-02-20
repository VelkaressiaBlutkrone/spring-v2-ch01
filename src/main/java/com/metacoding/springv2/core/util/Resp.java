package com.metacoding.springv2.core.util;

import lombok.Data;
import org.springframework.http.*;

/**
 * 통일된 API 응답 래퍼 클래스.
 * 모든 REST API 응답을 {status, msg, body} 형태로 표준화한다.
 * 성공(ok)과 실패(fail) 정적 팩토리 메서드를 통해 ResponseEntity를 생성한다.
 */
@Data
public class Resp<T> {
    private Integer status;
    private String msg;
    private T body;

    public Resp(Integer status, String msg, T body) {
        this.status = status;
        this.msg = msg;
        this.body = body;
    }

    // 성공 응답(200) 생성
    public static <B> ResponseEntity<Resp<B>> ok(B body) {
        Resp<B> resp = new Resp<>(200, "성공", body);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    // 실패 응답 생성
    public static ResponseEntity<?> fail(HttpStatus status, String msg) {
        Resp<?> resp = new Resp<>(status.value(), msg, null);
        return new ResponseEntity<>(resp, status);
    }
}