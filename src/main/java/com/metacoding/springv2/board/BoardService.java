package com.metacoding.springv2.board;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 게시글 비즈니스 로직 서비스 클래스.
 * 게시글 CRUD 트랜잭션 처리를 담당한다.
 */
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 게시글 전체 목록을 조회하여 DTO 리스트로 반환한다.
     * @return BoardResponse.ListDTO의 리스트
     */
    @Transactional(readOnly = true)
    public List<BoardResponse.ListDTO> getBoardList() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
                .map(board -> new BoardResponse.ListDTO(board))
                .collect(Collectors.toList());
    }

}
