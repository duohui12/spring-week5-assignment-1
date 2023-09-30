package com.codesoom.assignment.product.adapter.in.web;

import com.codesoom.assignment.exception.ProductNotFoundException;
import com.codesoom.assignment.product.ProductFixture;
import com.codesoom.assignment.product.application.port.in.GetProductUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GetProductController.class)
class GetProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetProductUseCase getProductUseCase;

    @Nested
    @DisplayName("Get /products/{id}")
    class Describe_get_request{

        @Nested
        @DisplayName("장난감이 존재하면")
        class Context_with_existing_id{

            @BeforeEach
            void setup(){
                given(getProductUseCase.getProduct(ProductFixture.EXISTING_ID))
                        .willReturn(ProductFixture.PRODUCT);
            }

            @Test
            @DisplayName("상태코드 200과 id로 찾은 장난감을 리턴한다.")
            void it_returns_200_and_product_found_by_id() throws Exception {
                mockMvc.perform(get("/products/" + ProductFixture.EXISTING_ID))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("id").value(ProductFixture.EXISTING_ID))
                        .andExpect(jsonPath("name").value(ProductFixture.PRODUCT_NAME));
            }

        }

        @Nested
        @DisplayName("장난감이 존재하지 않으면")
        class Context_with_not_existing_id{
            @BeforeEach
            void setup(){
                given(getProductUseCase.getProduct(ProductFixture.NOT_EXISTING_ID))
                        .willThrow(ProductNotFoundException.class);
            }

            @Test
            @DisplayName("상태코드 404를 리턴한다.")
            void it_returns_404() throws Exception {
                mockMvc.perform(get("/products/" + ProductFixture.NOT_EXISTING_ID))
                        .andExpect(status().isNotFound());
            }
        }

    }

}
