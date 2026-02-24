package com.metacoding.springv2.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.metacoding.springv2.auth.AuthRequest;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UserIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @DisplayName("회원가입 성공 테스트")
    @Test
    public void join_test() throws Exception {
        // given
        AuthRequest.JoinDTO joinDTO = new AuthRequest.JoinDTO("love", "1234", "love@nate.com");
        String requestBody = om.writeValueAsString(joinDTO);

        // when
        ResultActions actions = mvc.perform(
                post("/join")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON));

        // eye (then)
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.msg").value("성공"))
                .andExpect(jsonPath("$.body.username").value("love"))
                .andDo(print());
    }

    @DisplayName("로그인 성공 테스트")
    @Test
    public void login_test() throws Exception {
        // given
        AuthRequest.LoginDTO loginDTO = new AuthRequest.LoginDTO("ssar", "1234");
        String requestBody = om.writeValueAsString(loginDTO);

        // when
        ResultActions actions = mvc.perform(
                post("/login")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON));

        // eye (then)
        actions.andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("회원정보 보기 테스트")
    @WithUserDetails("ssar")
    @Test
    public void findUserById_test() throws Exception {
        // given
        int id = 1;

        // when
        ResultActions actions = mvc.perform(get("/api/users/" + id));

        // eye (then)
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.body.username").value("ssar"))
                .andDo(print());
    }
}
