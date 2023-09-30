package com.codesoom.assignment.product.application.port.in;

import com.codesoom.assignment.product.domain.Product;

public interface GetProductUseCase {
    Product getProduct(Long id);
}
