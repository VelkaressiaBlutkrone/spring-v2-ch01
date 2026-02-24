package com.metacoding.springv2.board;

import lombok.Data;

/**
 * 게시글 수정 요청 DTO 클래스.
 * 클라이언트로부터 수정할 제목과 내용을 받는다.
 */
@Data
public class BoardUpdateDTO {
    private String title;
    private String content;
}
