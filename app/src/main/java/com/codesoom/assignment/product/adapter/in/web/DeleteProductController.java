package com.codesoom.assignment.product.adapter.in.web;

import com.codesoom.assignment.product.application.port.in.DeleteProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@CrossOrigin
@RequiredArgsConstructor
class DeleteProductController {

    private final DeleteProductUseCase deleteProductUseCase;

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void destroy(@PathVariable Long id) {
        deleteProductUseCase.deleteProduct(id);
    }
}


