package com.codesoom.assignment.product.application.port.in;

import com.codesoom.assignment.product.domain.Product;

public interface UpdateProductUseCase {
    Product updateProduct(Long id, Product product);
}
