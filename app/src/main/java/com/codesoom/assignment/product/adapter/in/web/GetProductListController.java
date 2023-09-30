package com.codesoom.assignment.product.adapter.in.web;

import com.codesoom.assignment.product.application.port.in.GetProductListUseCase;
import com.codesoom.assignment.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin
@RequiredArgsConstructor
class GetProductListController {

    private final GetProductListUseCase getProductListUseCase;

    @GetMapping
    List<Product> list() {
        return getProductListUseCase.getProducts();
    }

}
