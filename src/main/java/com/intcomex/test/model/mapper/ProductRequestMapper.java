package com.intcomex.test.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.intcomex.test.model.dto.request.ProductRequest;
import com.intcomex.test.model.entity.Product;


@Mapper(componentModel = "spring") 
public interface ProductRequestMapper {

    @Mapping(source = "productName", target = "productName")
    @Mapping(source = "quantityPerUnit", target = "quantityPerUnit")
    @Mapping(source = "unitPrice", target = "unitPrice")
    @Mapping(source = "unitsInStock", target = "unitsInStock")
    @Mapping(source = "unitsOnOrder", target = "unitsOnOrder")
    @Mapping(source = "reorderLevel", target = "reorderLevel")
    Product toDomain(ProductRequest productRequest);
    
}
