package com.codesoom.assignment.product.application.service;

import com.codesoom.assignment.product.ProductFixture;
import com.codesoom.assignment.product.application.port.out.SaveProductPort;
import com.codesoom.assignment.product.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class RegisterProductServiceTest {

    private RegisterProductService registerProductService;
    private SaveProductPort saveProductPort;



    @BeforeEach
    void setup(){
        saveProductPort = mock(SaveProductPort.class);
        registerProductService = new RegisterProductService(saveProductPort);

        given(saveProductPort.save(any(Product.class))).willReturn(ProductFixture.PRODUCT);
    }

    @Test
    void registerProduct() {
        Product product = new Product();
        Product createdProduct = registerProductService.registerProduct(product);

        assertThat(createdProduct.getName()).isEqualTo(ProductFixture.PRODUCT.getName());
        assertThat(createdProduct.getMaker()).isEqualTo(ProductFixture.PRODUCT.getMaker());
        assertThat(createdProduct.getId()).isEqualTo(ProductFixture.PRODUCT.getId());
        assertThat(createdProduct.getPrice()).isEqualTo(ProductFixture.PRODUCT.getPrice());
    }
}
