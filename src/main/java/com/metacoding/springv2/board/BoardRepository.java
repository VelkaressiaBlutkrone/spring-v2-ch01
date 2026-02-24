package com.metacoding.springv2.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

/**
 * 게시글 JPA 리포지토리 인터페이스.
 * Board 엔티티에 대한 데이터 접근 계층을 담당하며, 기본적인 CRUD 메서드를 JpaRepository로부터 상속받는다.
 */
public interface BoardRepository extends JpaRepository<Board, Integer> {

    /**
     * 게시글 상세 조회 시 작성자(User) 정보를 한 번에 가져오기 위한 fetch join 쿼리.
     * N+1 문제를 방지하기 위해 사용한다.
     * @param id 조회할 게시글 ID
     * @return User가 페치 조인된 Board의 Optional 객체
     */
    @Query("select b from Board b join fetch b.user u where b.id = :id")
    Optional<Board> mFindByIdWithUser(@Param("id") Integer id);

}
