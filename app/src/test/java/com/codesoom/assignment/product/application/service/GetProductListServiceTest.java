package com.codesoom.assignment.product.application.service;

import com.codesoom.assignment.product.ProductFixture;
import com.codesoom.assignment.product.application.port.out.GetProductListPort;
import com.codesoom.assignment.product.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetProductListServiceTest {

    private GetProductListService getProductListService;
    private GetProductListPort getProductListPort;

    @BeforeEach
    void setup(){
        getProductListPort = mock(GetProductListPort.class);
        getProductListService = new GetProductListService(getProductListPort);

        List<Product> list = new ArrayList<>();
        list.add(ProductFixture.PRODUCT);
        given(getProductListPort.findAll()).willReturn(list);
    }

    @Test
    void getProducts() {
        assertThat(getProductListPort.findAll()).isNotEmpty();
        assertThat(getProductListPort.findAll()).hasSize(1);
    }
}
