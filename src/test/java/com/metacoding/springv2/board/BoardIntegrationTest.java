package com.metacoding.springv2.board;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class BoardIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @DisplayName("게시글 목록 보기 테스트")
    @WithUserDetails("ssar")
    @Test
    public void list_test() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(get("/api/boards"));

        // eye (then)
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.body[0].title").value("title1"))
                .andDo(print());
    }

    @DisplayName("게시글 상세보기 테스트")
    @WithUserDetails("ssar")
    @Test
    public void detail_test() throws Exception {
        // given
        int id = 1;

        // when
        ResultActions actions = mvc.perform(get("/api/boards/" + id));

        // eye (then)
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.body.title").value("title1"))
                .andExpect(jsonPath("$.body.username").value("ssar"))
                .andDo(print());
    }

    @DisplayName("게시글 수정하기 테스트")
    @WithUserDetails("ssar")
    @Test
    public void update_test() throws Exception {
        // given
        int id = 1;
        BoardUpdateDTO updateDTO = new BoardUpdateDTO();
        updateDTO.setTitle("제목수정");
        updateDTO.setContent("내용수정");
        String requestBody = om.writeValueAsString(updateDTO);

        // when
        ResultActions actions = mvc.perform(
                put("/api/boards/" + id)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON));

        // eye (then)
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.body.title").value("제목수정"))
                .andExpect(jsonPath("$.body.content").value("내용수정"))
                .andDo(print());
    }

    @DisplayName("게시글 삭제하기 테스트")
    @WithUserDetails("ssar")
    @Test
    public void delete_test() throws Exception {
        // given
        int id = 1;

        // when
        ResultActions actions = mvc.perform(delete("/api/boards/" + id));

        // eye (then)
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andDo(print());
    }
}
