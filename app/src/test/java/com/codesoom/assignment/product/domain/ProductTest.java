package com.codesoom.assignment.product.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    @Test
    void change() {

        //given
        Product product = new Product();
        product.setId(1L);
        product.setName("test name");
        product.setMaker("test maker");
        product.setPrice(1000);
        product.setImageUrl("test image url");

        Product source = new Product();
        source.setName("updated name");

        //when
        product.change(source.getName(), source.getMaker(), source.getPrice(), source.getImageUrl() );

        //then
        assertThat(product.getName()).isEqualTo("updated name");
        assertThat(product.getMaker()).isEqualTo("test maker");
        assertThat(product.getImageUrl()).isNull();
    }

}
