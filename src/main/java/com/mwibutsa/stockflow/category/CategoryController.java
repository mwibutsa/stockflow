package com.mwibutsa.stockflow.category;

import com.mwibutsa.stockflow.common.ErrorDto;
import com.mwibutsa.stockflow.common.exception.CustomException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getCategories() {
        var categories = categoryService.getCategories();
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable UUID categoryId) {
        var category = categoryService.getCategory(categoryId);
        return ResponseEntity.ok().body(category);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(
            @Valid @RequestBody CategoryRequest payload,
            UriComponentsBuilder uriBuilder) {
        var newCategory = categoryService.createCategory(payload);
        var uri = uriBuilder.path("/categories/{categoryId}").buildAndExpand(newCategory.getId()).toUri();
        return ResponseEntity.created(uri).body(newCategory);
    }

    @PutMapping("/{categoryId}")
    public CategoryResponse updateCategory(@PathVariable UUID categoryId,
                                           @Valid @RequestBody CategoryRequest payload) {

        return categoryService.updateCategory(categoryId, payload);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }


    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorDto> exceptionHandler(CustomException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(new ErrorDto(ex.getMessage()));
    }
}
