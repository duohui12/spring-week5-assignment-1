package com.codesoom.assignment.product.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long id;

    private String name;

    private String maker;

    private Integer price;

    private String imageUrl;

    public void change(String name,
                       String maker,
                       Integer price,
                       String imageUrl) {
        if(name != null) this.name = name;
        if(maker != null) this.maker = maker;
        if(price != null) this.price = price;
        this.imageUrl = imageUrl;
    }
}
