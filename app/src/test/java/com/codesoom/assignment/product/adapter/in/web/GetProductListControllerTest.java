package com.codesoom.assignment.product.adapter.in.web;

import com.codesoom.assignment.product.ProductFixture;
import com.codesoom.assignment.product.application.port.in.GetProductListUseCase;
import com.codesoom.assignment.product.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GetProductListController.class)
class GetProductListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetProductListUseCase getProductListUseCase;


    @Nested
    @DisplayName("Get /products")
    class Describe_get_list_request{

        @Nested
        @DisplayName("장난감이 존재할 때")
        class Context_with_products{

            @BeforeEach
            void setup(){
                List<Product> list = new ArrayList<>();
                list.add(ProductFixture.PRODUCT);
                given(getProductListUseCase.getProducts()).willReturn(list);
            }

            @Test
            @DisplayName("상태코드 200과 장난감 리스틀르 리턴한다.")
            void it_returns_200_and_product_list() throws Exception {
                mockMvc.perform(get("/products"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$[0].id").value(ProductFixture.EXISTING_ID))
                        .andExpect(jsonPath("$[0].name").value(ProductFixture.PRODUCT_NAME));
            }

        }

    }

}
