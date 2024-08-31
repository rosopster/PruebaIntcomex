package com.intcomex.test.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="suppliers")
@Data
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplierid")
    private Long supplierId;
    @Column(name = "companyname")
    private String companyName;
    @Column(name = "contactname")
    private String contactName;
    @Column(name = "contacttitle")
    private String contactTitle;
    private String address;
    private String city;
    private String region;
    @Column(name = "postalcode")
    private String postalCode;
    private String country;
    private String phone;
    private String fax;
    @Column(name = "homepage")
    private String homePage;
}
