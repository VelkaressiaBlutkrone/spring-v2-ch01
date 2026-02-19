package com.metacoding.springv2.reply;

import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;
import com.metacoding.springv2.board.Board;
import com.metacoding.springv2.user.User;
import jakarta.persistence.*;
import lombok.*;

/**
 * 댓글 엔티티 클래스.
 * reply_tb 테이블과 매핑되며, 댓글 내용(comment)을 가진다.
 * User와 N:1 관계, Board와 N:1 관계를 갖는다.
 */
@NoArgsConstructor
@Getter
@Entity
@Table(name = "reply_tb")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 100, nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Reply(Integer id, String comment, User user, Board board, Timestamp createdAt) {
        this.id = id;
        this.comment = comment;
        this.user = user;
        this.board = board;
        this.createdAt = createdAt;
    }

}