package com.codesoom.assignment.product.adapter.out.persistence;

import com.codesoom.assignment.exception.ProductNotFoundException;
import com.codesoom.assignment.product.application.port.out.DeleteProductPort;
import com.codesoom.assignment.product.application.port.out.GetProductListPort;
import com.codesoom.assignment.product.application.port.out.GetProductPort;
import com.codesoom.assignment.product.application.port.out.SaveProductPort;
import com.codesoom.assignment.product.domain.Product;
import com.github.dozermapper.core.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
class ProductPersistenceAdapter implements
        DeleteProductPort
        , SaveProductPort
        , GetProductListPort
        , GetProductPort {

    private final ProductSpringDataRepository productSpringDataRepository;
    private final Mapper mapper;
    @Override
    public void delete(Product product) {
        productSpringDataRepository.delete(mapper.map(product, ProductEntity.class));
    }

    @Override
    public List<Product> findAll() {
        return productSpringDataRepository.findAll().stream()
                .map(p -> mapper.map(p,Product.class)).collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id) {
        ProductEntity productEntity =  productSpringDataRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return mapper.map(productEntity, Product.class);
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = productSpringDataRepository.save(mapper.map(product,ProductEntity.class));
        return mapper.map(productEntity, Product.class);
    }
}
