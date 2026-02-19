package com.metacoding.springv2.board;

import java.sql.Timestamp;
import java.util.*;
import org.hibernate.annotations.CreationTimestamp;
import com.metacoding.springv2.reply.Reply;
import com.metacoding.springv2.user.User;
import jakarta.persistence.*;
import lombok.*;

/**
 * 게시글 엔티티 클래스.
 * board_tb 테이블과 매핑되며, 제목(title)과 내용(content)을 가진다.
 * User와 N:1 관계, Reply와 1:N 관계(cascade 삭제)를 갖는다.
 */
@NoArgsConstructor
@Getter
@Entity
@Table(name = "board_tb")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 30, nullable = false)
    private String title;
    @Column(length = 300, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @CreationTimestamp
    private Timestamp createdAt;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Reply> replies = new ArrayList<>();

    // 게시글 제목과 내용 수정
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Builder
    public Board(Integer id, String title, String content, User user, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
    }

}