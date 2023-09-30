package com.codesoom.assignment.product.application.service;

import com.codesoom.assignment.product.application.port.in.GetProductUseCase;
import com.codesoom.assignment.product.application.port.out.GetProductPort;
import com.codesoom.assignment.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class GetProductService implements GetProductUseCase {

    private final GetProductPort getProductPort;

    @Override
    public Product getProduct(Long id) {
        return getProductPort.findById(id);
    }
}
