package com.mwibutsa.stockflow.product;

import com.mwibutsa.stockflow.category.CategoryRepository;
import com.mwibutsa.stockflow.common.PaginatedResponse;
import com.mwibutsa.stockflow.common.exception.BadRequestException;
import com.mwibutsa.stockflow.common.exception.ConflictException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    public PaginatedResponse<ProductResponse> getAllProducts(String search, Pageable pageable) {
        var spec = ProductSpecification.searchProducts(search);
        IO.println(spec);
        var products = productRepository.findAll(spec, pageable);
        return productMapper.toPageResponse(products);
    }

    public ProductResponse createProduct(ProductRequest payload) {
        var category = categoryRepository.findById(payload.getCategoryId()).orElseThrow(BadRequestException::new);
        var barcodeInUse = productRepository.existsByBarcode(payload.getBarcode());

        if (barcodeInUse) {
            throw new ConflictException("Barcode in use", "barcode");
        }
        var product = productMapper.toEntity(payload);
        product.setCategory(category);
        product.setSku(this.generateUniqueSku(category.getName(), product.getName()));
        productRepository.save(product);

        return productMapper.toDto(product);
    }

    private String generateUniqueSku(String categoryName, String productName) {
        // 1. Get first 3 letters of category and product (fallback to "GEN" / "PRD" if short)
        String catPrefix = formatToken(categoryName, "GEN");
        String namePrefix = formatToken(productName, "PRD");

        String sku;
        Random random = new Random();

        // 2. Loop until we find a SKU that doesn't exist in the database
        do {
            int randomNumber = random.nextInt(9000) + 1000; // Generates a 4-digit number (1000-9999)
            sku = catPrefix + "-" + namePrefix + "-" + randomNumber;
        } while (productRepository.existsBySku(sku));

        return sku;
    }

    private String formatToken(String input, String defaultVal) {
        if (input == null || input.isBlank()) {
            return defaultVal;
        }
        String cleaned = input.replaceAll("[^a-zA-Z]", "").toUpperCase();
        if (cleaned.length() < 3) {
            return String.format("%-" + 3 + "s", cleaned).replace(' ', 'X');
        }
        return cleaned.substring(0, 3);
    }
}
