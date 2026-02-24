package com.metacoding.springv2.reply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * 댓글 JPA 리포지토리 인터페이스.
 * Reply 엔티티에 대한 데이터 접근 계층을 담당하며, 기본적인 CRUD 메서드를 JpaRepository로부터 상속받는다.
 */
public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    /**
     * 특정 게시글의 댓글 목록을 작성자(User) 정보와 함께 조회한다.
     * N+1 문제를 방지하기 위해 fetch join을 사용한다.
     * @param boardId 게시글 ID
     * @return User가 페치 조인된 Reply 리스트
     */
    @Query("select r from Reply r join fetch r.user u where r.board.id = :boardId")
    List<Reply> mFindByBoardIdWithUser(@Param("boardId") Integer boardId);

}
