package com.testing.science.controller;

import com.testing.science.dto.ProductDto;
import com.testing.science.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto dto){
        var product = productService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Long id , @Valid @RequestBody ProductDto dto){
        var product = productService.update(id,dto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(product);
    }

}