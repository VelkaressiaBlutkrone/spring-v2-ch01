package com.metacoding.springv2.reply;

import lombok.Data;

/**
 * 댓글 응답 DTO 클래스.
 * 댓글 조회 시 클라이언트에 반환할 응답 데이터를 정의한다.
 */
public class ReplyResponse {

    /**
     * 댓글 목록 조회를 위한 DTO 클래스.
     * id, 내용, 작성자 ID, 작성자 유저네임을 포함한다.
     */
    @Data
    public static class ListDTO {
        private Integer id;
        private String comment;
        private Integer userId;
        private String username;

        public ListDTO(Reply reply) {
            this.id = reply.getId();
            this.comment = reply.getComment();
            this.userId = reply.getUser().getId();
            this.username = reply.getUser().getUsername();
        }
    }

}
