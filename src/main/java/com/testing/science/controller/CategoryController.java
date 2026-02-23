package com.testing.science.controller;

import com.testing.science.dto.CategoryDto;
import com.testing.science.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> create(@Valid @RequestBody CategoryDto dto){
        var cate = categoryService.createCategory(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cate);
    }

    @GetMapping
    public List<CategoryDto> getAllCategory(){
        return categoryService.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> update(@Valid @RequestBody CategoryDto dto, @PathVariable Long id){
        var cate = categoryService.update(dto,id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(cate);
    }

}
