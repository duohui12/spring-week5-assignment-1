package com.codesoom.assignment.product.application.port.in;

import com.codesoom.assignment.product.domain.Product;

import java.util.List;

public interface GetProductListUseCase {
     List<Product> getProducts();
}
