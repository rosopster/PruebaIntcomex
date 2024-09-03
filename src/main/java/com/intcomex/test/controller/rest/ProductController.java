package com.intcomex.test.controller.rest;

import java.io.IOException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intcomex.test.model.dto.ProductDTO;
import com.intcomex.test.model.dto.request.ProductRequest;
import com.intcomex.test.model.service.IProductService;
import com.intcomex.test.model.service.ICategoryService;

@RestController
@RequestMapping("/api")
public class ProductController {
    
    private final IProductService iProductService;
    private final ICategoryService iCategoryService;

    public ProductController(IProductService iProductService, ICategoryService iCategoryService) {
        this.iProductService = iProductService;
        this.iCategoryService = iCategoryService;
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductRequest productRequest) {
        ProductDTO savedProduct = iProductService.createProduct(productRequest);
        return new ResponseEntity<>(savedProduct, HttpStatus.OK);
    }

    @GetMapping("/products")
    public Page<ProductDTO> listProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return iProductService.listProducts(pageable);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) throws IOException {
        ProductDTO product = iProductService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}

