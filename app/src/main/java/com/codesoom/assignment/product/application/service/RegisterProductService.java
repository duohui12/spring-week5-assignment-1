package com.codesoom.assignment.product.application.service;

import com.codesoom.assignment.product.application.port.in.RegisterProductUseCase;
import com.codesoom.assignment.product.application.port.out.SaveProductPort;
import com.codesoom.assignment.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class RegisterProductService implements RegisterProductUseCase {

    private final SaveProductPort saveProductPort;

    @Override
    public Product registerProduct(Product product) {
        return saveProductPort.save(product);
    }
}
