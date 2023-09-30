package com.codesoom.assignment.product.application.service;

import com.codesoom.assignment.product.application.port.in.DeleteProductUseCase;
import com.codesoom.assignment.product.application.port.out.DeleteProductPort;
import com.codesoom.assignment.product.application.port.out.GetProductPort;
import com.codesoom.assignment.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class DeleteProductService implements DeleteProductUseCase {

    private final GetProductPort getProductPort;
    private final DeleteProductPort deleteProductPort;

    @Override
    public void deleteProduct(Long id) {
        Product product = getProductPort.findById(id);
        deleteProductPort.delete(product);
    }
}
