package com.codesoom.assignment.product.adapter.in.web;

import com.codesoom.assignment.product.application.port.in.RegisterProductUseCase;
import com.codesoom.assignment.product.application.port.in.UpdateProductUseCase;
import com.codesoom.assignment.product.domain.Product;
import com.github.dozermapper.core.Mapper;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/products")
@CrossOrigin
@RequiredArgsConstructor
class UpdateProductController {
    private final UpdateProductUseCase updateProductUseCase;
    private final Mapper mapper;

    @PatchMapping("{id}")
    Product update(
            @PathVariable Long id,
            @RequestBody @Valid UpdateProductRequest updateProductRequest
    ) {
        return updateProductUseCase.updateProduct(id, mapper.map(updateProductRequest, Product.class));
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class UpdateProductRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String maker;

    @NotNull
    private Integer price;

    private String imageUrl;

}
