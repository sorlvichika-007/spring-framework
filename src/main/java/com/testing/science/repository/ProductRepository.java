package com.testing.science.repository;

import com.testing.science.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByCategoryId(Long categoryId);
}
