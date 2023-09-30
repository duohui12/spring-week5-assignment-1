package com.codesoom.assignment.product.application.service;

import com.codesoom.assignment.exception.ProductNotFoundException;
import com.codesoom.assignment.product.ProductFixture;
import com.codesoom.assignment.product.application.port.out.GetProductPort;
import com.codesoom.assignment.product.application.port.out.SaveProductPort;
import com.codesoom.assignment.product.domain.Product;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class UpdateProductServiceTest {

    private UpdateProductService updateProductService;
    private GetProductPort getProductPort;
    private SaveProductPort saveProductPort;

    @BeforeEach
    void setup(){
        saveProductPort = mock(SaveProductPort.class);
        getProductPort = mock(GetProductPort.class);
        updateProductService = new UpdateProductService(saveProductPort, getProductPort);

        given(getProductPort.findById(ProductFixture.EXISTING_ID)).willReturn(ProductFixture.PRODUCT);
        given(getProductPort.findById(ProductFixture.NOT_EXISTING_ID)).willThrow(ProductNotFoundException.class);
        given(saveProductPort.save(any(Product.class))).willReturn(ProductFixture.SOURCE);
    }

    @Test
    void updateProductWithExistingId() {

        Product updatedProduct = updateProductService.updateProduct(ProductFixture.EXISTING_ID, ProductFixture.SOURCE);

        assertThat(updatedProduct.getId()).isEqualTo(ProductFixture.EXISTING_ID);
        assertThat(updatedProduct.getName()).isEqualTo(ProductFixture.UPDATED_PRODUCT_NAME);

        verify(getProductPort).findById(ProductFixture.EXISTING_ID);
        verify(saveProductPort).save(ProductFixture.PRODUCT);
    }

}
