package com.codesoom.assignment.product.application.service;

import com.codesoom.assignment.exception.ProductNotFoundException;
import com.codesoom.assignment.product.ProductFixture;
import com.codesoom.assignment.product.application.port.out.GetProductListPort;
import com.codesoom.assignment.product.application.port.out.GetProductPort;
import com.codesoom.assignment.product.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetProductServiceTest {

    private GetProductService getProductService;
    private GetProductPort getProductPort;

    @BeforeEach
    void setup(){
        getProductPort = mock(GetProductPort.class);
        getProductService = new GetProductService(getProductPort);

        given(getProductPort.findById(ProductFixture.EXISTING_ID)).willReturn(ProductFixture.PRODUCT);
        given(getProductPort.findById(ProductFixture.NOT_EXISTING_ID)).willThrow(ProductNotFoundException.class);
    }

    @Test
    void getProductWithExistingId() {
        assertThat(getProductService.getProduct(ProductFixture.EXISTING_ID).getName())
                .isEqualTo(ProductFixture.PRODUCT_NAME);
    }

    @Test
    void getProductWithNotExistingId(){
        assertThatThrownBy(() -> getProductService.getProduct(ProductFixture.NOT_EXISTING_ID))
                .isInstanceOf(ProductNotFoundException.class);
    }
}
