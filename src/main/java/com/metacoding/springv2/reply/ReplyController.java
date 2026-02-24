package com.metacoding.springv2.reply;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.metacoding.springv2.core.util.Resp;
import lombok.RequiredArgsConstructor;
import java.util.List;

/**
 * 댓글 관련 REST 컨트롤러.
 * 댓글 CRUD API 엔드포인트를 제공한다.
 */
@RequiredArgsConstructor
@RestController
public class ReplyController {

    private final ReplyService replyService;

    /**
     * 특정 게시글의 댓글 목록 조회 API.
     * GET /api/boards/{boardId}/replies 요청을 처리한다.
     * @param boardId 게시글 ID
     * @return 댓글 목록 DTO 리스트를 포함한 Resp.ok 응답
     */
    @GetMapping("/api/boards/{boardId}/replies")
    public ResponseEntity<?> list(@PathVariable Integer boardId) {
        List<ReplyResponse.ListDTO> responseDTOs = replyService.getReplyList(boardId);
        return Resp.ok(responseDTOs);
    }
}
