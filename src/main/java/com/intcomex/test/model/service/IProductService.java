package com.intcomex.test.model.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.intcomex.test.model.dto.ProductDTO;
import com.intcomex.test.model.dto.request.ProductRequest;

public interface IProductService {
    ProductDTO createProduct(ProductRequest productRequest);
    Page<ProductDTO> listProducts(Pageable pageable);
    ProductDTO getProductById(Long id);
    
}
