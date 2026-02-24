package com.metacoding.springv2.board;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.metacoding.springv2.core.util.Resp;
import lombok.RequiredArgsConstructor;
import java.util.List;

/**
 * 게시글 관련 REST 컨트롤러.
 * 게시글 CRUD API 엔드포인트를 제공한다.
 */
@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시글 목록 조회 API.
     * GET /api/boards 요청을 처리하여 게시글 목록을 반환한다.
     * @return 게시글 목록 DTO 리스트를 포함한 Resp.ok 응답
     */
    @GetMapping("/api/boards")
    public ResponseEntity<?> list() {
        List<BoardResponse.ListDTO> responseDTOs = boardService.getBoardList();
        return Resp.ok(responseDTOs);
    }

    /**
     * 게시글 상세 조회 API.
     * GET /api/boards/{id} 요청을 처리하여 특정 게시글의 상세 정보를 반환한다.
     * @param id 조회할 게시글 ID
     * @return 게시글 상세 정보 DTO를 포함한 Resp.ok 응답
     */
    @GetMapping("/api/boards/{id}")
    public ResponseEntity<?> detail(@PathVariable Integer id) {
        BoardDetailDTO responseDTO = boardService.getBoardDetail(id);
        return Resp.ok(responseDTO);
    }

    /**
     * 게시글 수정 API.
     * PUT /api/boards/{id} 요청을 처리하여 게시글을 수정한다.
     * @param id 수정할 게시글 ID
     * @param requestDTO 수정 데이터
     * @return 수정된 게시글 정보 DTO를 포함한 Resp.ok 응답
     */
    @PutMapping("/api/boards/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody BoardUpdateDTO requestDTO) {
        BoardDetailDTO responseDTO = boardService.updateBoard(id, requestDTO);
        return Resp.ok(responseDTO);
    }

    /**
     * 게시글 삭제 API.
     * DELETE /api/boards/{id} 요청을 처리하여 게시글을 삭제한다.
     * @param id 삭제할 게시글 ID
     * @return 성공 응답 (body: null)
     */
    @DeleteMapping("/api/boards/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        boardService.deleteBoard(id);
        return Resp.ok(null);
    }
}
