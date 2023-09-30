package com.codesoom.assignment.product.application.port.in;

import com.codesoom.assignment.product.domain.Product;

public interface RegisterProductUseCase {
    Product registerProduct(Product product);
}
