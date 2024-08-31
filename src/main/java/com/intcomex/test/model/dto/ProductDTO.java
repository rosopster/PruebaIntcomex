package com.intcomex.test.model.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    private Long id;
    private Long categoryId;  
    private Long supplierId;
    private String productName;
    private BigDecimal price;
    private String categoryName;
    private String categoryPicture;
}