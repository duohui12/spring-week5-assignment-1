package com.codesoom.assignment.product.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface ProductSpringDataRepository extends JpaRepository<ProductEntity, Long> {
}
