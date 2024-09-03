package com.intcomex.test.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.intcomex.test.model.dto.ProductDTO;
import com.intcomex.test.model.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductDtoMapper {

    @Mapping(source = "productId", target = "id")
    @Mapping(source = "productName", target = "productName")
    @Mapping(source = "unitPrice", target = "price")
    @Mapping(source = "category.categoryName", target = "categoryName")
    @Mapping(source = "category.categoryId", target = "categoryId")
    @Mapping(source = "category.picture", target = "categoryPicture")
    @Mapping(source = "supplier.supplierId", target = "supplierId")
    ProductDTO productToDto(Product product);

    // Mapeo de DTO a entidad, con conversiones específicas
    @Mapping(source = "categoryId", target = "category.categoryId")
    @Mapping(source = "supplierId", target = "supplier.supplierId")
    Product toProduct(ProductDTO productDTO);

}