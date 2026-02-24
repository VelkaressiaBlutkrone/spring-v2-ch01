package com.metacoding.springv2.board;

import lombok.Data;

/**
 * 게시글 상세 응답 DTO 클래스.
 * 내부 클래스를 사용하지 않고 독립적인 클래스로 구성한다.
 */
@Data
public class BoardDetailDTO {
    private Integer id;
    private String title;
    private String content;
    private Integer userId;
    private String username;

    public BoardDetailDTO(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.userId = board.getUser().getId();
        this.username = board.getUser().getUsername();
    }
}
