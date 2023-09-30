package com.codesoom.assignment.user.adapter.in.web;

import com.codesoom.assignment.exception.UserNotFoundException;
import com.codesoom.assignment.product.ProductFixture;
import com.codesoom.assignment.user.application.port.in.DeleteUserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeleteUserController.class)
class DeleteUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeleteUserUseCase deleteUserUseCase;

    @Nested
    @DisplayName("Delete /users/{id}")
    class Describe_delete_request{

        @Nested
        @DisplayName("존재하는 계정을 삭제하면")
        class Context_with_existing_id{

            @Test
            @DisplayName("상태코드 204을 리턴한다.")
            void it_returns_201() throws Exception {
                mockMvc.perform(delete("/users/1"))
                        .andExpect(status().isNoContent());

                verify(deleteUserUseCase).deleteUser(1L);
            }
        }

        @Nested
        @DisplayName("존재하지 않는 계정을 삭제하면")
        class Context_with_not_existing_id{

            @BeforeEach
            void setup(){
                Mockito.doThrow(UserNotFoundException.class).when(deleteUserUseCase).deleteUser(100L);
            }

            @Test
            @DisplayName("상태코드 404를 리턴한다.")
            void it_returns_404() throws Exception {
                mockMvc.perform(delete("/users/100"))
                        .andExpect(status().isNotFound());

                verify(deleteUserUseCase).deleteUser(100L);
            }
        }
    }

}
