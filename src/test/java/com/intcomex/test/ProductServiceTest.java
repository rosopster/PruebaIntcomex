package com.intcomex.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.intcomex.test.model.dto.ProductDTO;
import com.intcomex.test.model.entity.Product;
import com.intcomex.test.model.repository.ProductRepository;
import com.intcomex.test.model.service.impl.ProductServiceImpl;

@SpringBootTest
public class ProductServiceTest {
    
    @MockBean
    private ProductRepository productRepository;
    
    @Autowired
    private ProductServiceImpl productService;

    /* @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setProductName("Test Product");
        
        when(productRepository.save(any(Product.class))).thenReturn(product);
        
        ProductDTO savedProduct = productService.createProduct(product);
        
        assertEquals("Test Product", savedProduct.getProductName());
    } */
}

