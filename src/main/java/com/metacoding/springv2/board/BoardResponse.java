package com.metacoding.springv2.board;

import lombok.Data;

/**
 * 게시글 응답 DTO 클래스.
 * 게시글 조회 시 클라이언트에 반환할 응답 데이터를 정의한다.
 */
public class BoardResponse {

    /**
     * 게시글 목록 조회를 위한 DTO 클래스.
     * id, 제목, 내용을 포함한다.
     */
    @Data
    public static class ListDTO {
        private Integer id;
        private String title;
        private String content;

        public ListDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
        }
    }

}
