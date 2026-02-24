package com.metacoding.springv2.board;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metacoding.springv2.core.handler.ex.Exception404;

import lombok.RequiredArgsConstructor;

/**
 * 게시글 비즈니스 로직 서비스 클래스.
 * 게시글 CRUD 트랜잭션 처리를 담당한다.
 */
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 게시글 전체 목록을 조회하여 DTO 리스트로 반환한다.
     *
     * @return BoardResponse.ListDTO의 리스트
     */
    public List<BoardResponse.ListDTO> getBoardList() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
                .map(board -> new BoardResponse.ListDTO(board))
                .collect(Collectors.toList());
    }

    /**
     * 게시글 상세 정보를 조회하여 DTO로 반환한다.
     * 작성자 정보를 포함하기 위해 fetch join이 적용된 repository 메서드를 사용한다.
     *
     * @param id 조회할 게시글 ID
     * @return BoardDetailDTO
     * @throws Exception404 해당 ID의 게시글을 찾을 수 없는 경우 발생
     */
    public BoardDetailDTO getBoardDetail(Integer id) {
        Board board = boardRepository.mFindByIdWithUser(id)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));
        return new BoardDetailDTO(board);
    }

    /**
     * 게시글을 수정한다.
     *
     * @param id         수정할 게시글 ID
     * @param requestDTO 수정할 데이터 (제목, 내용)
     * @return 수정된 게시글 상세 정보 DTO
     * @throws Exception404 해당 ID의 게시글을 찾을 수 없는 경우 발생
     */
    @Transactional
    public BoardDetailDTO updateBoard(Integer id, BoardUpdateDTO requestDTO) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        board.update(requestDTO.getTitle(), requestDTO.getContent());

        return new BoardDetailDTO(board);
    } // 더티 체킹에 의해 업데이트됨

    /**
     * 게시글을 삭제한다.
     *
     * @param id 삭제할 게시글 ID
     * @throws Exception404 해당 ID의 게시글을 찾을 수 없는 경우 발생
     */
    @Transactional
    public void deleteBoard(Integer id) {
        boardRepository.findById(id)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        boardRepository.deleteById(id);
    }

}
