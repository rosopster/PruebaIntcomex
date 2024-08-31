package com.intcomex.test.model.dto.request;

import java.math.BigDecimal;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private String productName;
    private Long supplierId;
    private Long categoryId;
    private String quantityPerUnit;
    private BigDecimal unitPrice;
    private Integer unitsInStock;
    private Integer unitsOnOrder;
    private Integer reorderLevel;

}