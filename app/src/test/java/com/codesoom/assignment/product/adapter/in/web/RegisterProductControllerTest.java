package com.codesoom.assignment.product.adapter.in.web;

import com.codesoom.assignment.product.ProductFixture;
import com.codesoom.assignment.product.application.port.in.RegisterProductUseCase;
import com.codesoom.assignment.product.domain.Product;
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
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegisterProductController.class)
class RegisterProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RegisterProductUseCase registerProductUseCase;

    @Nested
    @DisplayName("POST /products")
    class Describe_create_product{

        @Nested
        @DisplayName("유효한 속성값을 넘겨주면")
        class Context_with_valid_attributes{

            RegisterProductRequest validRequest;

            @BeforeEach
            void setup(){

                validRequest = new RegisterProductRequest(
                        ProductFixture.PRODUCT_NAME
                        , ProductFixture.PRODUCT_MAKER
                        , ProductFixture.PRODUCT_PRICE
                        , ProductFixture.PRODUCT_IMAGE_URL
                );

                given(registerProductUseCase.registerProduct(any(Product.class)))
                        .willReturn(ProductFixture.PRODUCT);
            }

            @Test
            @DisplayName("상태코드 200과 추가된 상품을 리턴한다.")
            void it_returns_200_and_created_product() throws Exception {
                mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest)))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("id").value(ProductFixture.EXISTING_ID));

                verify(registerProductUseCase).registerProduct(any(Product.class));
            }

        }

        @Nested
        @DisplayName("유효하지 않은 속성값을 넘겨주면")
        class Context_with_invalid_attributes{

            RegisterProductRequest invalidRequest;

            @BeforeEach
            void setup(){

                invalidRequest = new RegisterProductRequest();

                given(registerProductUseCase.registerProduct(any(Product.class)))
                        .willReturn(ProductFixture.PRODUCT);
            }

            @Test
            @DisplayName("상태코드 400을 리턴한다. ")
            void it_returns_400() throws Exception {
                mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                        .andExpect(status().isBadRequest());

                //verify(registerProductUseCase).registerProduct(any(Product.class));
            }
        }

    }
}
