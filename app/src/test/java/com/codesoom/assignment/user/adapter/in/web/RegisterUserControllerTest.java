package com.codesoom.assignment.user.adapter.in.web;

import com.codesoom.assignment.user.application.port.in.RegisterUserUseCase;
import com.codesoom.assignment.user.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegisterUserController.class)
class RegisterUserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private RegisterUserUseCase registerUserUseCase;


    private static final RegisterUserRequest validRequeset = new RegisterUserRequest("dh", "dh@gmail.com","1111");
    private static final RegisterUserRequest invalidRequeset = new RegisterUserRequest();
    private static final User user = new User(1L,"dh","dh@gmail.com","1111");


    @Nested
    @DisplayName("POST /users")
    class Describe_create_user{

        @Nested
        @DisplayName("유효한 속성값을 넘겨주면")
        class Context_with_valid_attributes{

            @BeforeEach
            void setup(){
                given(registerUserUseCase.registerUser(any(User.class))).willReturn(user);
            }

            @Test
            @DisplayName("상태코드 200과 생성된 계정을 리턴한다.")
            void it_returns_200_and_created_user() throws Exception {
                mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequeset)))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("id").value(1L));
            }
        }

        @Nested
        @DisplayName("유효하지 않은 속성값을 넘겨주면")
        class Context_with_invalid_attributes{

            @Test
            @DisplayName("상태코드 400을 리턴한단.")
            void it_returns_200_and_created_user() throws Exception {
                mockMvc.perform(post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(invalidRequeset)))
                        .andExpect(status().isBadRequest());
            }
        }
    }
}
