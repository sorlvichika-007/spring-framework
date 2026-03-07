package com.testing.science.service.impl;

import com.testing.science.dto.ProductDto;
import com.testing.science.exceptions.ResourceNotFoundException;
import com.testing.science.mapper.ProductMapper;
import com.testing.science.model.Category;
import com.testing.science.model.Product;
import com.testing.science.repository.CategoryRepository;
import com.testing.science.repository.ProductRepository;
import com.testing.science.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    @Override
    public ProductDto create(ProductDto dto) {
        var category = getCategoryId(dto.categoryId());
        var product = productMapper.toEntity(dto);
        product.setCategory(category);
        productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public ProductDto update(Long id, ProductDto dto) {
        var category = getCategoryId(dto.categoryId());
        var product = getId(id);
        productMapper.updateProduct(dto,product);
        product.setCategory(category);
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public List<ProductDto> getAllProduct() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public ProductDto getById(Long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return productMapper.toDto(product);
    }

    private Product getId(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not found"));
    }

    private Category getCategoryId(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category Not found"));
    }

}