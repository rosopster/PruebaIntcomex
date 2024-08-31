package com.intcomex.test.model.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/* public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    
    private String productName;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
    
} */
@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productid")
    private Long productId;

    @Column(name = "productname", nullable = false)
    private String productName;

    @ManyToOne
    @JoinColumn(name = "supplierid", referencedColumnName = "supplierid")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "categoryid", referencedColumnName = "categoryid")
    private Category category;

    @Column(name = "quantityperunit")
    private String quantityPerUnit;

    @Column(name = "unitprice")
    private BigDecimal unitPrice;

    @Column(name = "unitsinstock")
    private Integer unitsInStock;

    @Column(name = "unitsonorder")
    private Integer unitsOnOrder;

    @Column(name = "reorderlevel")
    private Integer reorderLevel;

    @Column(name = "discontinued", nullable = false)
    private boolean discontinued;

}