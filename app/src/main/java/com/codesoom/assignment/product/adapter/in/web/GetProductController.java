package com.codesoom.assignment.product.adapter.in.web;

import com.codesoom.assignment.product.application.port.in.GetProductUseCase;
import com.codesoom.assignment.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@CrossOrigin
@RequiredArgsConstructor
class GetProductController {

    private final GetProductUseCase getProductUseCase;

    @GetMapping("{id}")
    Product detail(@PathVariable Long id) {
        return getProductUseCase.getProduct(id);
    }

}
