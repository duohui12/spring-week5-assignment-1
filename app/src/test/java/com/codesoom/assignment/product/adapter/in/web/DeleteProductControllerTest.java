package com.codesoom.assignment.product.adapter.in.web;

import com.codesoom.assignment.exception.ProductNotFoundException;
import com.codesoom.assignment.product.ProductFixture;
import com.codesoom.assignment.product.application.port.in.DeleteProductUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeleteProductController.class)
class DeleteProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeleteProductUseCase deleteProductUseCase;

    @Nested
    @DisplayName("Delete /products/{id}")
    class Describe_delete_request{

        @Nested
        @DisplayName("장난감을 찾을 수 있으면")
        class Context_with_existing_id{

            @Test
            @DisplayName("상태코드 204를 응답한다.")
            void it_returns_204() throws Exception {
                mockMvc.perform(delete("/products/" + ProductFixture.EXISTING_ID))
                        .andExpect(status().isNoContent());

            }
        }

        @Nested
        @DisplayName("장난감을 찾을 수 없으면")
        class Context_with_not_existing_id{

            @BeforeEach
            void setup(){
                Mockito.doThrow(ProductNotFoundException.class).when(deleteProductUseCase).deleteProduct(ProductFixture.NOT_EXISTING_ID);
            }

            @Test
            @DisplayName("상태코드 404를 응답한다.")
            void it_returns_204() throws Exception {
                mockMvc.perform(delete("/products/" + ProductFixture.NOT_EXISTING_ID))
                        .andExpect(status().isNotFound());

            }
        }

    }


}
