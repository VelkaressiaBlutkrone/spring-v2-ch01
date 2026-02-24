package com.metacoding.springv2.reply;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 댓글 비즈니스 로직 서비스 클래스.
 * 댓글 CRUD 트랜잭션 처리를 담당한다.
 */
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    /**
     * 특정 게시글의 댓글 목록을 조회하여 DTO 리스트로 반환한다.
     * N+1 문제 방지를 위해 fetch join이 적용된 repository 메서드를 사용한다.
     * @param boardId 게시글 ID
     * @return ReplyResponse.ListDTO의 리스트
     */
    public List<ReplyResponse.ListDTO> getReplyList(Integer boardId) {
        List<Reply> replies = replyRepository.mFindByBoardIdWithUser(boardId);
        return replies.stream()
                .map(reply -> new ReplyResponse.ListDTO(reply))
                .collect(Collectors.toList());
    }

}
