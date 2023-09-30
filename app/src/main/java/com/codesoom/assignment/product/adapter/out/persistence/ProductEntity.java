package com.codesoom.assignment.product.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class ProductEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String maker;

    private Integer price;

    private String imageUrl;
}
