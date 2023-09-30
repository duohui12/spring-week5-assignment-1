package com.codesoom.assignment.product.application.service;

import com.codesoom.assignment.product.application.port.in.GetProductListUseCase;
import com.codesoom.assignment.product.application.port.out.GetProductListPort;
import com.codesoom.assignment.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
class GetProductListService implements GetProductListUseCase {

    private final GetProductListPort getProductListPort;

    @Override
    public List<Product> getProducts() {
       return getProductListPort.findAll();
    }
}
