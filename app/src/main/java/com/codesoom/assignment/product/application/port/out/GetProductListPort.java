package com.codesoom.assignment.product.application.port.out;


import com.codesoom.assignment.product.domain.Product;

import java.util.List;

public interface GetProductListPort {
    List<Product> findAll();
}
