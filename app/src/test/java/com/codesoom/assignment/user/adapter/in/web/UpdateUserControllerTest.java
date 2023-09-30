package com.codesoom.assignment.user.adapter.in.web;

import com.codesoom.assignment.exception.UserNotFoundException;
import com.codesoom.assignment.user.application.port.in.UpdateUserUseCase;
import com.codesoom.assignment.user.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import javax.print.attribute.standard.MediaSize;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UpdateUserController.class)
class UpdateUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UpdateUserUseCase updateUserUseCase;

    private static final String NAME = "dh";
    private static final String EMAIL = "dh@gmail.com";
    private static final String PASSWORD = "1111";

    private static final String UPDATED_NAME = "dh12";
    private static final String UPDATED_EMAIL = "dh12@gmail.com";
    private static final String UPDATE_PASSWORD = "2222";

    private static final Long EXISTING_ID = 1L;
    private static final Long NOT_EXISTING_ID = 100L;

    @Nested
    @DisplayName("PATCH /users/{id}")
    class Describe_update_request{

        @Nested
        @DisplayName("유효한 속성값을 넘겨주면")
        class Context_with_valid_attributes{

            UpdateUserRequest source;
            User updatedUser;

            @BeforeEach
            void setup(){
                source = new UpdateUserRequest(UPDATED_NAME,UPDATE_PASSWORD);
                updatedUser = new User(EXISTING_ID, EMAIL, UPDATED_NAME, UPDATE_PASSWORD);

                given(updateUserUseCase.updateUser(eq(EXISTING_ID),any(User.class)))
                        .willReturn(updatedUser);
            }

            @Test
            @DisplayName("상태코드 200과 업데이트된 계정 정보를 리턴한다.")
            void it_returns_200_and_updated_user_data() throws Exception {
                mockMvc.perform(patch("/users/"+EXISTING_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(source)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("id").value(EXISTING_ID))
                        .andExpect(jsonPath("name").value(UPDATED_NAME))
                        .andExpect(jsonPath("email").value(EMAIL));
            }

        }

        @Nested
        @DisplayName("유효하지 않은 속성값을 넘겨주면")
        class Context_with_invalid_attributes{

            UpdateUserRequest source;

            @BeforeEach
            void setup(){
                source = new UpdateUserRequest();
            }

            @Test
            @DisplayName("상태코드 400을 리턴한다.")
            void it_returns_400() throws Exception {
                mockMvc.perform(patch("/users/"+EXISTING_ID)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(source)))
                        .andExpect(status().isBadRequest());
            }

        }

        @Nested
        @DisplayName("존재하지 않는 아이디를 넘겨주면")
        class Context_with_not_existing_id{

            UpdateUserRequest source;

            @BeforeEach
            void setup(){
                source = new UpdateUserRequest(UPDATED_NAME,UPDATE_PASSWORD);

                given(updateUserUseCase.updateUser(eq(NOT_EXISTING_ID),any(User.class)))
                        .willThrow(UserNotFoundException.class);
            }

            @Test
            @DisplayName("상태코드 404를 리턴한다.")
            void it_returns_404() throws Exception {
                mockMvc.perform(patch("/users/"+NOT_EXISTING_ID)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(source)))
                        .andExpect(status().isNotFound());

            }
        }
    }

}
