package com.codesoom.assignment.product.adapter.in.web;

import com.codesoom.assignment.exception.ProductNotFoundException;
import com.codesoom.assignment.product.ProductFixture;
import com.codesoom.assignment.product.application.port.in.UpdateProductUseCase;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UpdateProductController.class)
class UpdateProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UpdateProductUseCase updateProductUseCase;

    private static final UpdateProductRequest validRequest = new UpdateProductRequest(
            ProductFixture.UPDATED_PRODUCT_NAME
            , ProductFixture.UPDATED_PRODUCT_MAKER
            , ProductFixture.UPDATED_PRODUCT_PRICE
            , ProductFixture.UPDATED_PRODUCT_IMAGE_URL );

    private static final UpdateProductRequest invalidRequest = new UpdateProductRequest();

    @Nested
    @DisplayName("PATCH /products")
    class Describe_patch_request{

        @Nested
        @DisplayName("유효한 속성값을 넘겨주면")
        class Context_with_valid_attributes{

            @BeforeEach
            void setup(){
                given(updateProductUseCase.updateProduct(eq(ProductFixture.EXISTING_ID), any(Product.class)))
                        .willReturn(ProductFixture.SOURCE);
            }

            @Test
            @DisplayName("상태코드 200과 업데이트된 장난감을 리턴한다.")
            void it_returns_200_and_updated_product() throws Exception {
                mockMvc.perform(patch("/products/"+ProductFixture.EXISTING_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("id").value(ProductFixture.EXISTING_ID))
                        .andExpect(jsonPath("name").value(ProductFixture.UPDATED_PRODUCT_NAME));

                verify(updateProductUseCase).updateProduct(eq(ProductFixture.EXISTING_ID) , any(Product.class));
            }

        }

        @Nested
        @DisplayName("장나감을 찾을 수 없으면")
        class Context_with_not_existing_id{

            @BeforeEach
            void setup(){
                given(updateProductUseCase.updateProduct(eq(ProductFixture.NOT_EXISTING_ID), any(Product.class)))
                        .willThrow(ProductNotFoundException.class);
            }

            @Test
            @DisplayName("상태코드 404를 리턴한다.")
            void it_returns() throws Exception {
                mockMvc.perform(patch("/products/"+ProductFixture.NOT_EXISTING_ID)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(validRequest)))
                        .andExpect(status().isNotFound());

                verify(updateProductUseCase).updateProduct(eq(ProductFixture.NOT_EXISTING_ID) , any(Product.class));
            }
        }

        @Nested
        @DisplayName("유효하지 않은 속성값을 넘겨주면")
        class Context_with_invalid_attributes{

            @Test
            @DisplayName("상태코드 400을 리턴한다.")
            void it_returns_400() throws Exception {
                mockMvc.perform(patch("/products/"+ProductFixture.EXISTING_ID)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(invalidRequest)))
                        .andExpect(status().isBadRequest());
            }
        }

    }


}
