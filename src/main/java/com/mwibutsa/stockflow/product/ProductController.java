package com.mwibutsa.stockflow.product;

import com.mwibutsa.stockflow.common.PaginatedResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public PaginatedResponse<ProductResponse> getAllProducts(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String direction
    ) {
        // SORT DIRECTION
        Sort.Direction sortDir = Sort.Direction.fromOptionalString(direction).orElse(Sort.Direction.DESC);

        // Pageable
        Pageable pagination = PageRequest.of(page, size, Sort.by(sortDir, sortBy));
        return productService.getAllProducts(search, pagination);

    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest payload, UriComponentsBuilder uriBuilder) {
        var product = productService.createProduct(payload);
        var uri = uriBuilder.path("/products/{productId}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(product);
    }
}
