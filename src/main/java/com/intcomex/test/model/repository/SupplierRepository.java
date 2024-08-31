package com.intcomex.test.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intcomex.test.model.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
