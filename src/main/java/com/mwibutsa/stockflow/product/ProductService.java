package com.mwibutsa.stockflow.product;

import com.mwibutsa.stockflow.common.PaginatedResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public PaginatedResponse<ProductResponse> getAllProducts(String search, Pageable pageable) {
        var spec = ProductSpecification.searchProducts(search);
        var products = productRepository.findAll(spec, pageable);
        return productMapper.toPageResponse(products);
    }
}
