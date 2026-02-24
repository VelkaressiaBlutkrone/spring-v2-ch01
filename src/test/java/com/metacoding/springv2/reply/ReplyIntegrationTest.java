package com.metacoding.springv2.reply;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ReplyIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @DisplayName("게시글 댓글 목록 보기 테스트")
    @WithUserDetails("ssar")
    @Test
    public void list_test() throws Exception {
        // given
        int boardId = 4;

        // when
        ResultActions actions = mvc.perform(get("/api/boards/" + boardId + "/replies"));

        // eye (then)
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.body[0].comment").value("comment1"))
                .andExpect(jsonPath("$.body[0].username").value("cos"))
                .andDo(print());
    }
}
