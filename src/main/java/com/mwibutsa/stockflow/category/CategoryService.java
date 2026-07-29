package com.mwibutsa.stockflow.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    public List<CategoryResponse> getCategories() {
        var categories = categoryRepository.findAll();
        return categories.stream().map(categoryMapper::toDto).toList();
    }

    public CategoryResponse getCategory(UUID categoryId) {
        var category = categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
        return categoryMapper.toDto(category);
    }

    public CategoryResponse createCategory(CategoryRequest payload) {

        var existingCategory = categoryRepository.findByName(payload.getName()).orElse(null);

        if (existingCategory != null) {
            throw new CategoryExistsException();
        }

        var category = categoryMapper.toEntity(payload);
        categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }

    public CategoryResponse updateCategory(UUID categoryId, CategoryRequest payload) {
        var category = categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);

        // check if name not taken.
        var conflictingCategory = categoryRepository.findByName(payload.getName()).orElse(null);

        if (conflictingCategory != null && conflictingCategory.getId() != category.getId()) {
            throw new CategoryExistsException();
        }
        categoryMapper.update(payload, category);
        categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }
}
