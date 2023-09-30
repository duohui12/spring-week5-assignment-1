package com.codesoom.assignment.product.application.port.out;


import com.codesoom.assignment.product.domain.Product;

public interface GetProductPort {
    Product findById(Long id);
}
