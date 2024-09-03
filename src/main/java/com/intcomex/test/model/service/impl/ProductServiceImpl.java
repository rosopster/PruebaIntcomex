package com.intcomex.test.model.service.impl;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.intcomex.test.common.constants.Constants;
import com.intcomex.test.common.exception.ItemNotFoundException;
import com.intcomex.test.model.dto.ProductDTO;
import com.intcomex.test.model.dto.request.ProductRequest;
import com.intcomex.test.model.mapper.ProductDtoMapper;
import com.intcomex.test.model.mapper.ProductRequestMapper;
import com.intcomex.test.model.repository.CategoryRepository;
import com.intcomex.test.model.repository.ProductRepository;
import com.intcomex.test.model.repository.SupplierRepository;
import com.intcomex.test.model.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService{
    
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;
    private final ProductDtoMapper productDtoMapper;
    private final ProductRequestMapper productRequestMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductDtoMapper productDtoMapper, ProductRequestMapper productRequestMapper, SupplierRepository supplierRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
        this.productDtoMapper = productDtoMapper;
        this.productRequestMapper = productRequestMapper;
    }

    @Override
    public ProductDTO createProduct(ProductRequest productRequest) {
        
        var newProductRequest = productRequestMapper.toDomain(productRequest);
        
        var optionalCategory = categoryRepository.findById(productRequest.getCategoryId());
        if (optionalCategory.isEmpty()){
            throw new ItemNotFoundException(HttpStatus.NOT_FOUND, String.format(Constants.CATEGORY_NOT_FOUND_MESSAGE_ERROR, productRequest.getCategoryId()));
        }

        var optionalSupplier = supplierRepository.findById(productRequest.getSupplierId());
        if (optionalSupplier.isEmpty()){
            throw new ItemNotFoundException(HttpStatus.NOT_FOUND, String.format(Constants.SUPPLIER_NOT_FOUND_MESSAGE_ERROR, productRequest.getSupplierId()));
        }
        newProductRequest.setCategory(optionalCategory.get());
        newProductRequest.setSupplier(optionalSupplier.get());
        var createdProduct = productRepository.save(newProductRequest);
        return productDtoMapper.productToDto(createdProduct);
    }

    @Override
    public Page<ProductDTO> listProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(productDtoMapper::productToDto);
    }
    
    @Override
    public ProductDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(productDtoMapper::productToDto)
                .orElseThrow(() -> new ItemNotFoundException(HttpStatus.NOT_FOUND, String.format(Constants.PRODUCT_NOT_FOUND_MESSAGE_ERROR, id)));
    }
}
