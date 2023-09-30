package com.codesoom.assignment.product.application.service;

import com.codesoom.assignment.product.application.port.in.UpdateProductUseCase;
import com.codesoom.assignment.product.application.port.out.GetProductPort;
import com.codesoom.assignment.product.application.port.out.SaveProductPort;
import com.codesoom.assignment.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class UpdateProductService implements UpdateProductUseCase {

    private final SaveProductPort saveProductPort;
    private final GetProductPort getProductPort;

    @Override
    public Product updateProduct(Long id, Product source) {
        Product product = getProductPort.findById(id);
        product.change(source.getName()
                        , source.getMaker()
                        , source.getPrice()
                        , source.getImageUrl());
        return saveProductPort.save(product);
    }
}
