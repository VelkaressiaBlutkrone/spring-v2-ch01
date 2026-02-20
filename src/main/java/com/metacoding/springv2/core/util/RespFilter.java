package com.metacoding.springv2.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import java.io.*;

/**
 * 필터 레벨 에러 응답 유틸리티 클래스.
 * Spring Security 필터 체인에서 발생하는 인증/인가 오류 시
 * 컨트롤러를 거치지 않고 직접 JSON 형태의 에러 응답을 작성하여 클라이언트에 반환한다.
 */
@Slf4j
public class RespFilter {
    private static ObjectMapper om = new ObjectMapper();

    // HTTP 응답에 에러 JSON 직접 작성
    public static void fail(HttpServletResponse response, int status, String msg) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json;charset=utf-8");

        Resp<?> resp = new Resp<>(status, msg, null);

        String responseBody = null;
        try {
            responseBody = om.writeValueAsString(resp);
        } catch (JsonProcessingException e) {
            log.error("JSON 변환 실패", e);
            responseBody = """
                    {"status":500, "msg":"서버 내부 오류", "body":null}
                    """;
            ;
        }

        PrintWriter out = response.getWriter();
        out.println(responseBody);
        out.flush();
    }
}