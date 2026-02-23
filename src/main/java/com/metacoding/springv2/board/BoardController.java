package com.metacoding.springv2.board;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
