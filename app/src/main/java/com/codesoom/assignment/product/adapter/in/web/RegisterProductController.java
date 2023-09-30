package com.codesoom.assignment.product.adapter.in.web;

import com.codesoom.assignment.product.application.port.in.RegisterProductUseCase;
import com.codesoom.assignment.product.domain.Product;
import com.github.dozermapper.core.Mapper;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/products")
@CrossOrigin
@RequiredArgsConstructor
class RegisterProductController {

    private final RegisterProductUseCase registerProductUseCase;
    private final Mapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Product create(@RequestBody @Valid RegisterProductRequest registerProductRequest) {
        return registerProductUseCase.registerProduct(mapper.map(registerProductRequest, Product.class));
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class RegisterProductRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String maker;

    @NotNull
    private Integer price;

    private String imageUrl;

}
